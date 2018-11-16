package com.jiangfeng.chart.component;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.ScaleData;

import java.util.List;

/**
 * Created by LWH
 * 2018/11/13 16:12
 * X轴线
 */
public class XAxis extends AxisBase<String> {
    private final String TAG = XAxis.class.getName();

    /**
     * 画X轴基准轴
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param rect      图表范围
     * @param chartData 图表数据
     */
    @Override
    public void drawAxis(Canvas canvas, Paint paint, Rect rect, ChartData chartData) {
        float textHeight = getTextHeight(getScaleTextStyle().fillPaint(paint));
        xZero = rect.left + textHeight;
        yZero = (int) (rect.bottom - textHeight);
        canvas.drawLine(xZero, yZero, rect.right, yZero, getAxisStyle().fillPaint(paint));
    }

    /**
     * 绘制刻度文字和X轴刻度
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param chartRect 图表区域
     * @param chartData 图表数据
     */
    @Override
    public void drawScaleText(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        List<String> xDataList = chartData.getxDataList();
        //刻度的范围
        ScaleData scaleData = chartData.getScaleData();
        scaleData.getRect().bottom = (int) getTextHeight(getScaleTextStyle().fillPaint(paint));
        int xSize = xDataList.size();
        double perWidth = (chartRect.right - chartRect.left) / (isLine() ? xSize - 1 : xSize);
        for (int position = 0; position < xSize; position++) {
            String content = xDataList.get(position);
            //X轴坐标=X轴原点坐标+行宽-文字宽度/2;
            int xStart = (int) ((xZero + (position + 1) * perWidth) - paint.measureText(content) / 2);
            int yStart = chartRect.bottom;
//            Log.i(TAG, "--x:" + xStart + "--y:" + yStart + "--content:" + content);
            canvas.drawText(content, xStart, yStart, getScaleTextStyle().fillPaint(paint));

        }
    }

    @Override
    public void drawScaleLine(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        int xSize = chartData.getxDataList().size();
        //刻度的范围
        ScaleData scaleData = chartData.getScaleData();
        scaleData.getRect().bottom = (int) getTextHeight(getScaleTextStyle().fillPaint(paint));
        paint.setStrokeWidth(1);
        paint.setColor(Color.parseColor("#76abc4"));
        double perWidth = (chartRect.right - chartRect.left) / (isLine() ? xSize - 1 : xSize);
        for (int position = 1; position < xSize; position++) {
            int xStart = (int) (xZero + position * perWidth);
            paint.setPathEffect(new DashPathEffect(new float[]{1, 2, 4, 8}, 0));
//        Log.i(TAG, "--x:" + xStart + "--y:" + yStart + "--right:" + rect.right + "--bottom:" + rect.bottom);
            canvas.drawLine(xStart, yZero, xStart, chartRect.top, paint);
        }
    }


}
