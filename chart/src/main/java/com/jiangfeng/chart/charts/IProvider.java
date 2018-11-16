package com.jiangfeng.chart.charts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jiangfeng.chart.data.ChartData;

public interface IProvider {
    /**
     * 绘制内容
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param chartRect 图表范围
     */
    void drawProvider(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData);
}
