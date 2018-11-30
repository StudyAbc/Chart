package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.style.LineStyle;

/**
 * Created by LWH
 * 2018/11/30 12:01
 * 饼图
 */
public class PieChart extends PieRadarChartBase<Double> implements IProvider<Double> {
    /**
     * 外切圆范围
     */
    private RectF mRectF;

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    IProvider<Double> initProvider() {
        return this;
    }


    @Override
    public void drawProvider(Canvas canvas, Paint paint, Rect chartRect, ChartData<Double> chartData) {
        int centerX = chartRect.centerX();
        int centerY = chartRect.centerY();
        mRectF = new RectF(chartRect);
        mRectF.left=centerX;
        mRectF.top=centerY;
        LineStyle sectorStyle = new LineStyle();
        sectorStyle.setStyle(Paint.Style.FILL_AND_STROKE);

//        canvas.drawCircle(centerX, centerY, 100, sectorStyle.fillPaint(paint));
        canvas.drawArc(mRectF, -90, 90, false, sectorStyle.fillPaint(paint));
    }
}
