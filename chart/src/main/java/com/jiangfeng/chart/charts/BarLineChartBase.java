package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.jiangfeng.chart.component.XAxis;
import com.jiangfeng.chart.component.YAxis;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnChartChangeListener;
import com.jiangfeng.chart.matrix.MatrixHelper;

/**
 * 线形图和柱状图的父类;
 */
public abstract class BarLineChartBase<T> extends BaseChart<T> implements OnChartChangeListener {
    private final String TAG = BarLineChartBase.class.getName();
    /**
     * 横坐标轴
     */
    protected XAxis mXAxis;
    /**
     * 竖坐标轴
     */
    protected YAxis mYAxis;
    /**
     * 显示坐标点
     */
    private boolean mShowPoint;
    /**
     * 显示坐标点的值
     */
    private boolean mShowPointValue;

    public BarLineChartBase(Context context) {
        super(context);
        init();
    }

    public BarLineChartBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarLineChartBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化坐标轴
     */
    private void init() {
        mXAxis = new XAxis();
        mYAxis = new YAxis();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mChartData != null) {
            computeChartRect();
            //绘制坐标轴
            mXAxis.drawAxis(canvas, mPaint, mChartRect, mChartData);
            mXAxis.drawScale(canvas, mPaint, mChartRect, mMatrixHelper, mChartData);

            //设置Y轴刻度线条数
            mYAxis.drawAxis(canvas, mPaint, mChartRect, mChartData);
            mYAxis.drawScale(canvas, mPaint, mChartRect, mMatrixHelper, mChartData);

            //绘制坐标点，和图表
            iProvider.drawProvider(canvas, mPaint, mChartRect, mChartData);
        }
    }

    public void setShowXScaleLine(boolean showXScaleLine) {
        mXAxis.setShowXScaleLine(showXScaleLine);
    }

    public void setShowYScaleLine(boolean showYScaleLine) {
        mYAxis.setShowYScaleLine(showYScaleLine);
    }

    public void setXScaleSize(int xScaleSize) {
        mXAxis.setxScaleSize(xScaleSize);
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

    public void setChartData(ChartData<T> chartData) {
        this.mChartData = chartData;
        mMatrixHelper.setChartData(mChartData);
        invalidate();
    }

    public void setZoom(boolean isZoom) {
        mMatrixHelper.setZoom(isZoom);
    }

    public MatrixHelper getMatrixHelper() {
        return mMatrixHelper;
    }

    public boolean isShowPointValue() {
        return mShowPointValue;
    }

    public void setShowPointValue(boolean showPointValue) {
        this.mShowPointValue = showPointValue;
    }
}
