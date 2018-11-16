package com.jiangfeng.chart.matrix;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.jiangfeng.chart.listener.OnChartChangeListener;

/**
 * Created by LWH
 * 2018/11/16 14:32
 * 图表缩放处理
 */
public class MatrixHelper {
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
    private float mTranslateX;
    /**
     * Y轴移动距离，基准点在左上角
     */
    private float mTranslateY;
    /**
     * 图表变化监听
     */
    private OnChartChangeListener chartChangeListener;

    public MatrixHelper(Context context) {
        //UI标准常量类
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return false;
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
                notifyViewChanged();
                return true;
            }

            //手指离开屏幕，且滑动速度足够快时调用且仅调用一次该方法。注意：这个方法并不是当屏幕滚动时调用的
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityX) > mMinimumFlingVelocity) {
                    mScroller.setFinalX(0);
                    mScroller.setFinalY(0);
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            //双击时，第二次touch事件的down发生时就会执行。因此该MotionEvent中只有action_down。因为该方法只有在action_down中被调用。
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }

            //单击
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }
        });
    }

    public void setOnChartChangeListener(OnChartChangeListener chartChangeListener) {
        this.chartChangeListener = chartChangeListener;
    }

    /**
     * 通知刷新视图
     */
    private void notifyViewChanged() {
        Log.i(TAG, "--translateX:" + mTranslateX + "--translteY:" + mTranslateY);
        if (chartChangeListener != null) {
            chartChangeListener.onChartChange(mTranslateX, mTranslateY);
        }
    }

    /**
     * 处理触摸事件
     *
     * @param event 触摸事件
     */
    public void handlerTouchEvent(MotionEvent event) {
//        mGestrueDetector.onTouchEvent(event);
    }

    /**
     * 判断是否要接收缩放触摸事件
     *
     * @param event 触摸事件
     */
    public void onInterceptZoomEvent(MotionEvent event) {
        if (!isZoom) {
            return;
        }

    }
}
