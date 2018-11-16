package com.jiangfeng.chart.component;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.ColumnData;
import com.jiangfeng.chart.data.format.IFormat;

public interface IAxis<T> {
    /**
     * 画基准轴线
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    void drawAxis(Canvas canvas, Paint paint, Rect chartRect, ChartData<ColumnData> chartData);

    /**
     * 画刻度
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    void drawScaleText(Canvas canvas, Paint paint, Rect chartRect, ChartData<ColumnData> chartData);

    /**
     * 绘制刻度线
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param chartRect 图表范围
     * @param chartData 图表数据
     */
    void drawScaleLine(Canvas canvas, Paint paint, Rect chartRect, ChartData<ColumnData> chartData);

    /**
     * 设置格式化
     *
     * @param format 文本格式化
     */
    void setFormat(IFormat<T> format);

    /**
     * 获取格式化方式
     *
     * @return 格式化方式
     */
    IFormat<T> getFormat();

    /**
     * 计算文本高度
     *
     * @param paint 画笔
     * @return 文本高度
     */
    float getTextHeight(Paint paint);
}
