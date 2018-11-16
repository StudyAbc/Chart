package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jiangfeng.chart.component.XAxis;
import com.jiangfeng.chart.component.YAxis;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.ColumnData;
import com.jiangfeng.chart.util.DensityUtil;

/**
 * 线形图和柱状图的父类;
 */
public abstract class BarLineChartBase<T extends ColumnData> extends View implements IProvider {
    /**
     * 横坐标轴
     */
    protected XAxis mXAxis;
    /**
     * 竖坐标轴
     */
    protected YAxis mYAxis;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 图表数据
     */
    private ChartData<T> mChartData;
    /**
     * 图表区域
     */
    private Rect mChartRect;
    /**
     * 图表宽度
     */
    private int mChartWidth;
    /**
     * 图表高度
     */
    private int mChartHeight;
    /**
     * 图表内边距(像素)
     */
    private int mPadding = 50;
    private Context mContext;
    /**
     * 显示坐标点
     */
    private boolean mShowPoint;

    public BarLineChartBase(Context context) {
        super(context);
        init(context);
    }

    public BarLineChartBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BarLineChartBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mXAxis = new XAxis();
        mYAxis = new YAxis();
        mPaint = new Paint();
        mChartRect = new Rect();
        mContext = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mChartData != null) {
            computeChartRect();
            //绘制坐标轴
            mXAxis.drawAxis(canvas, mPaint, mChartRect, mChartData);
            mXAxis.drawScaleText(canvas, mPaint, mChartRect, mChartData);
            if (mXAxis.isShowXScaleLine()) {
                mXAxis.drawScaleLine(canvas, mPaint, mChartRect, mChartData);
            }
            //设置Y轴刻度线条数
            mYAxis.drawAxis(canvas, mPaint, mChartRect, mChartData);
            mYAxis.drawScaleText(canvas, mPaint, mChartRect, mChartData);
            if (mYAxis.isShowYScaleLine()) {
                mYAxis.drawScaleLine(canvas, mPaint, mChartRect, mChartData);
            }
            //绘制坐标点，和图表
            drawProvider(canvas, mPaint, mChartRect, mChartData);
        }
    }

    @Override
    public void drawProvider(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        drawProviderValue(canvas, paint, chartRect, chartData);
    }

    /**
     * 绘制坐标点，线形图或柱状图
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param chartRect 图表范围
     * @param chartData 图表数据
     */
    abstract void drawProviderValue(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData);

    public void setChartData(ChartData<T> chartData) {
        this.mChartData = chartData;
        invalidate();
    }

    /**
     * 尺寸变化
     *
     * @param w    当前宽度
     * @param h    当前高度
     * @param oldw 之前的宽度
     * @param oldh 之前的高度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mChartWidth = w;
        mChartHeight = h;
    }

    /**
     * 计算图表区域
     */
    private void computeChartRect() {
        mChartRect.left = mPadding;
        mChartRect.top = mPadding;
        mChartRect.right = mChartWidth - mPadding;
        mChartRect.bottom = mChartHeight - mPadding;
    }

    /**
     * 设置图表内边距 dp
     *
     * @param padding 内边距
     */
    public void setPadding(int padding) {
        this.mPadding = DensityUtil.dp2px(mContext, padding);
    }

    /**
     * 触摸事件
     *
     * @param event 事件
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }


    public void setShowXScaleLine(boolean showXScaleLine) {
        mXAxis.setShowXScaleLine(showXScaleLine);
    }

    public void setShowYScaleLine(boolean showYScaleLine) {
        mYAxis.setShowYScaleLine(showYScaleLine);
    }

    /**
     * 设置Y轴刻度个数
     *
     * @param yScaleSize Y轴刻度个数
     */
    public void setYScaleSize(int yScaleSize) {
        mYAxis.setyScaleSize(yScaleSize);
    }

    public boolean isShowPoint() {
        return mShowPoint;
    }

    public void setShowPoint(boolean showPoint) {
        this.mShowPoint = showPoint;
    }
}