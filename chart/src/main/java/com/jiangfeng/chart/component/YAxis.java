package com.jiangfeng.chart.component;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.ScaleData;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by LWH
 * 2018/11/14 10:11
 * Y轴
 */
public class YAxis extends AxisBase<Double> {
    private final String TAG = YAxis.class.getName();


    @Override
    public void drawAxis(Canvas canvas, Paint paint, Rect rect, ChartData chartData) {
        float textHeight = getTextHeight(getScaleTextStyle().fillPaint(paint));
        xZero = rect.left + textHeight;
        yZero = (int) (rect.bottom - textHeight);
        canvas.drawLine(xZero, yZero, rect.left + textHeight, rect.top, getAxisStyle().fillPaint(paint));
    }

    @Override
    public void drawScaleText(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        int perHeight = (int) (getYAxisHeight(chartRect) / getyScaleSize());
        ScaleData scaleData = chartData.getScaleData();
        List<Double> columnDataList = chartData.getColumnDataList();
        scaleData.setMinY(Collections.min(columnDataList));
        scaleData.setMaxY(Collections.max(columnDataList));
        int xStart = chartRect.left;
        for (int i = 0; i <= getyScaleSize(); i++) {
            //平均Y轴刻度
            String yScale = formatData((scaleData.getMaxY() / getyScaleSize()) * i);
            //Y轴坐标=图表底部坐标-行高-文字高度;
            int yStart = (int) (yZero - i * perHeight);
            canvas.drawText(yScale, xStart, yStart, getScaleTextStyle().fillPaint(paint));
        }

//        scaleData.getMaxY() / getyScaleSize() / getYAxisHeight(chartRect) / getyScaleSize() = value / h;
//        scaleData.getMaxY() / getYAxisHeight(chartRect) = value / h;
//        h = getYAxisHeight(chartRect) * value / scaleData.getMaxY();
    }


    @Override
    public void drawScaleLine(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        int perHeight = (int) (getYAxisHeight(chartRect) / getyScaleSize());
//        //刻度的范围
//        ScaleData scaleData = chartData.getScaleData();
//        scaleData.getRect().bottom = (int) getTextHeight(getScaleTextStyle().fillPaint(paint));
        paint.setStrokeWidth(1);
        paint.setColor(Color.parseColor("#bf68c7"));
//        Log.i(TAG, "--height:" + getYAxisHeight(chartRect) + "--size:" + getyScaleSize() + "--perHeight:" + perHeight);
        for (int position = 1; position <= getyScaleSize(); position++) {
            int yStart = (int) (yZero - position * perHeight);
            //虚线绘制
            paint.setPathEffect(new DashPathEffect(new float[]{1, 2, 4, 8}, 0));
            canvas.drawLine(xZero, yStart, chartRect.right, yStart, paint);
        }
    }

    /**
     * 计算Y轴数值对应的高度
     *
     * @param chartData 图表数据
     * @param chartRect 图表范围
     * @param value     坐标点数值
     * @return Y轴的高度
     */
    public float getYHeightByVlaue(ChartData chartData, Rect chartRect, double value) {
        return (float) (getYAxisHeight(chartRect) * value / chartData.getScaleData().getMaxY());
    }

    /**
     * 格式化Y轴数据
     *
     * @param data 原始数据
     * @return 格式化后的数据
     */
    public String formatData(double data) {
        if (getFormat() != null) {
            return getFormat().format(data);
        }
        //默认保留两位小数
        return new DecimalFormat("0.00").format(data);
    }

    /**
     * 获取Y轴绘制的高度
     *
     * @param chartRect 图表
     * @return 绘制的高度
     */
    public float getYAxisHeight(Rect chartRect) {
        return yZero - chartRect.top;
    }


}
