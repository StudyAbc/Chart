package com.jiangfeng.chart.charts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jiangfeng.chart.data.ChartData;

/**
 * Created by LWH
 * 2018/11/30 15:23
 * 内容绘制
 */
public interface IProvider<T> {
    /**
     * 绘制内容
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param chartRect 图表范围
     * @param chartData 图表数据
     */
    void drawProvider(Canvas canvas, Paint paint, Rect chartRect, ChartData<T> chartData);
}
