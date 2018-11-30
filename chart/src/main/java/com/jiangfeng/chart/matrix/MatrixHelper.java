package com.jiangfeng.chart.matrix;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.Scroller;

import com.jiangfeng.chart.charts.BaseChart;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnChartChangeListener;
import com.jiangfeng.chart.listener.OnClickColumnListener;

import java.util.Map;
import java.util.Set;

/**
 * Created by LWH
 * 2018/11/16 14:32
 * 图表缩放处理
 */
public class MatrixHelper<T> {
    private final String TAG = MatrixHelper.class.getName();
    /**
     * 最大缩放比例
     */
    private final static int MAX_ZOOM = 5;
    /**
     * 最小缩放比例
     */
    private final static int MIN_ZOOM = 1;
    /**
     * 当前缩放比例
     */
    private float currentZoom = MIN_ZOOM;
    /**
     * 是否支持缩放
     */
    private boolean isZoom = false;
    /**
     * 缩放手势
     */
    private ScaleGestureDetector mScaleGestureDetector;
    /**
     * 移动手势
     */
    private GestureDetector mGestrueDetector;
    /**
     * 滚动
     */
    private Scroller mScroller;
    /**
     * 每秒移动速率，单位px
     */
    private int mMinimumFlingVelocity;
    /**
     * X轴移动距离，基准点在左上角
     */
    private int mTranslateX;
    /**
     * Y轴移动距离，基准点在左上角
     */
    private int mTranslateY;
    /**
     * 屏幕手指点个数
     */
    private int mPointMode;
    /**
     * 点击时的X，Y坐标
     */
    private float mDownX;
    private float mDownY;
    private float mTempZoom = MIN_ZOOM;
    /**
     * 原始图表大小
     */
    private Rect mOriginalRect;
    /**
     * 图表变化监听
     */
    private OnChartChangeListener chartChangeListener;
    private OnClickColumnListener<T> clickColumnListener;
    private ChartData<T> chartData;

    public MatrixHelper(final Context context) {
        mScroller = new Scroller(context);
        //UI标准常量类
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float oldZoom = currentZoom;
                float scaleFactor = detector.getScaleFactor();
                currentZoom = (float) (mTempZoom * Math.pow(scaleFactor, 2));
                float factor = currentZoom / oldZoom;
                resetTranslate(factor);
                if (currentZoom < MIN_ZOOM) {
                    currentZoom = MIN_ZOOM;
                    return true;
                } else if (currentZoom > MAX_ZOOM) {
                    currentZoom = MAX_ZOOM;
                    return true;
                }
                return false;
            }

            /**
             * 缩放开始
             */
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                mTempZoom = currentZoom;
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });
        mGestrueDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            //滚动
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mTranslateX += distanceX;
                mTranslateY += distanceY;
                Log.i(TAG, "---GestureDetector mTranslateY:" + mTranslateX + "--mTranslateY:" + mTranslateY);
                notifyViewChanged();
                return true;
            }

            //手指离开屏幕，且滑动速度足够快时调用且仅调用一次该方法。注意：这个方法并不是当屏幕滚动时调用的
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityX) > mMinimumFlingVelocity || Math.abs(velocityY) > mMinimumFlingVelocity) {
                    mScroller.setFinalX(0);
                    mScroller.setFinalY(0);
                    mScroller.fling(0, 0, (int) velocityX, (int) velocityY, -5000, 50000, -50000, 50000);
                    notifyViewChanged();
                }
                return true;
            }

            //双击时，第二次touch事件的down发生时就会执行。因此该MotionEvent中只有action_down。因为该方法只有在action_down中被调用。
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            //单击
            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                int index = indexOfClick((int) event.getX(), (int) event.getY(), chartData);
                if (clickColumnListener != null && index != -1) {
                    clickColumnListener.onClickColumn(index, String.valueOf(chartData.getxDataList().get(index)), chartData.getColumnDataList().get(index));
                }
                return true;
            }
        });

    }

    /**
     * Created by LWH
     * 2018/11/29 17:06
     * 判断点击坐标在哪个区域内
     * 滑动时把可见的图形范围，和在图表数据的索引添加到MAP集合
     * 根据点击的坐标点判断在哪个图形范围内
     */
    private int indexOfClick(int x, int y, ChartData chartData) {
        Map<Integer, Rect> scaleRectMap = chartData.getScaleData().getScaleRectMap();
        Set<Integer> integers = scaleRectMap.keySet();
        for (Integer integer : integers) {
            Rect indexRect = scaleRectMap.get(integer);
            if (indexRect != null && indexRect.contains(x, y)) {
                return integer;
            }
        }
        return -1;
    }

    public void setClickColumnListener(OnClickColumnListener<T> clickColumnListener) {
        this.clickColumnListener = clickColumnListener;
    }

    public void setOnChartChangeListener(OnChartChangeListener chartChangeListener) {
        this.chartChangeListener = chartChangeListener;
    }

    /**
     * 通知刷新视图
     */
    private void notifyViewChanged() {
        if (chartChangeListener != null) {
            chartChangeListener.onChartChange(mTranslateX, mTranslateY);
        }
    }

    /**
     * 处理触摸事件
     *
     * @param event 触摸事件
     */
    public boolean handlerTouchEvent(MotionEvent event) {
        mGestrueDetector.onTouchEvent(event);
        if (isZoom) {
            mScaleGestureDetector.onTouchEvent(event);
        }
        return true;
    }

    /**
     * 判断是否要接收缩放触摸事件
     *
     * @param event 触摸事件
     */
    public void onDisallowInterceptEvent(BaseChart chart, MotionEvent event) {
        if (!isZoom) {
            return;
        }
        ViewParent parent = chart.getParent();
        if (mOriginalRect == null) {
            parent.requestDisallowInterceptTouchEvent(false);
            return;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                if (mOriginalRect.contains((int) mDownX, (int) mDownY)) {
                    parent.requestDisallowInterceptTouchEvent(true);
                } else {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mPointMode++;
                parent.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mPointMode > 1) {
                    parent.requestDisallowInterceptTouchEvent(true);
                    return;
                }
                boolean isDisallowIntercept = true;
                float distanceX = event.getX() - mDownX;
                float distanceY = event.getY() - mDownY;
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    isDisallowIntercept = true;
                } else {
                    isDisallowIntercept = false;
                }
                parent.requestDisallowInterceptTouchEvent(isDisallowIntercept);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mPointMode = -1;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mPointMode = 0;
                parent.requestDisallowInterceptTouchEvent(false);
                break;
        }
    }

    /**
     * 获取缩放的大小
     *
     * @param chartRect 图表范围
     * @return 缩放后的图表范围
     */
    public Rect getZoomRect(Rect chartRect, int xSize, int dataSize) {
        mOriginalRect = chartRect;
        Rect zoomRect = new Rect();
        int oldWidth = chartRect.width();
        int oldHeight = chartRect.height();
        //最大可滑动宽度
        int maxWidth = (dataSize * oldWidth) / xSize;
        int zoomWidth = (int) (currentZoom * maxWidth);
        int zoomHeight = (int) (currentZoom * oldHeight);
        int offsetX = (int) (oldWidth * (currentZoom - 1) / 2);
        int offsetY = (int) (oldHeight * (currentZoom - 1) / 2);
        int maxTranslateLeft = (int) Math.abs(oldWidth * (currentZoom - 1) / 2);
        int maxTranslateRight = zoomWidth - offsetX - oldWidth;
        int maxTranslateY = Math.abs(zoomHeight - oldHeight) / 2;
        Log.i(TAG, "--maxTranslateLeft:" + maxTranslateLeft + "--maxTranslateRight:" + maxTranslateRight + "--offsetX:" + offsetX + "--currentZoom:" + currentZoom);
        if (mTranslateX < -maxTranslateLeft) {
            mTranslateX = -maxTranslateLeft;
        }
        if (mTranslateX > maxTranslateRight) {
            mTranslateX = maxTranslateRight;
        }
        if (Math.abs(mTranslateY) > maxTranslateY) {
            if (mTranslateY > 0) {
                mTranslateY = maxTranslateY;
            } else {
                mTranslateY = -maxTranslateY;
            }
        }
        zoomRect.left = chartRect.left - mTranslateX - offsetX;
        zoomRect.top = chartRect.top - mTranslateY - offsetY;
        zoomRect.right = chartRect.left + zoomWidth;
        zoomRect.bottom = chartRect.top + zoomHeight;
        return zoomRect;
    }

    public int getTranslateX() {
        return mTranslateX;
    }

    public int getTranslateY() {
        return mTranslateY;
    }

    /**
     * 重新计算偏移量
     *
     * @param factor 倍数
     */
    private void resetTranslate(float factor) {
        mTranslateX = (int) (mTranslateX * factor);
        mTranslateY = (int) (mTranslateY * factor);
    }

    public void setZoom(boolean zoom) {
        isZoom = zoom;
    }

    public void setChartData(ChartData<T> chartData) {
        this.chartData = chartData;
    }
}
