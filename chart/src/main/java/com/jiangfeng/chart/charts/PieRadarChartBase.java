package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnChartChangeListener;

/**
 * Created by LWH
 * 2018/11/30 13:48
 * 饼图和雷达图的基类
 */
public abstract class PieRadarChartBase<T> extends BaseChart<T> implements OnChartChangeListener {
    /**
     * 显示坐标点
     */
    private boolean mShowPoint;
    /**
     * 显示坐标点的值
     */
    private boolean mShowPointValue;

    public PieRadarChartBase(Context context) {
        super(context);
    }

    public PieRadarChartBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieRadarChartBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mChartData != null) {
            computeChartRect();
            //绘制坐标点，和图表
            iProvider.drawProvider(canvas, mPaint, mChartRect, mChartData);
        }
    }

    public void setChartData(ChartData<T> chartData) {
        this.mChartData = chartData;
        mMatrixHelper.setChartData(mChartData);
        invalidate();
    }

}
