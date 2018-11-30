package com.jiangfeng.chart.component;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.style.FontStyle;
import com.jiangfeng.chart.data.style.LineStyle;
import com.jiangfeng.chart.matrix.MatrixHelper;

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
     * @param chartRect 图表范围
     * @param chartData 图表数据
     */
    @Override
    public void drawAxis(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        float textHeight = getTextHeight(getScaleTextStyle().setTextSize(36).fillPaint(paint));
        xZero = chartRect.left + textHeight;
        yZero = (int) (chartRect.bottom - textHeight);
        canvas.drawLine(xZero, yZero, chartRect.right, yZero, getAxisStyle().fillPaint(paint));
    }

    /**
     * 绘制刻度文字和X轴刻度
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param helper    图表缩放处理
     * @param chartRect 图表区域
     * @param chartData 图表数据
     */
    @Override
    public void drawScale(Canvas canvas, Paint paint, Rect chartRect, MatrixHelper helper, ChartData chartData) {
        List<String> xDataList = chartData.getxDataList();
        int xSize = getxScaleSize();
        Rect zoomRect = helper.getZoomRect(chartRect, xSize, xDataList.size());
        zoomRect.top = chartRect.top;
        zoomRect.bottom = chartRect.bottom;
        double perWidth = (chartRect.right - chartRect.left) / xSize;
        FontStyle scaleTextStyle = getScaleTextStyle();
        LineStyle gridStyle = getGridStyle();
        gridStyle.setWidth(2).setColor(Color.parseColor("#d8631b"))
                .setEffect(new DashPathEffect(new float[]{1, 2, 4, 8}, 0));
        for (int position = 0; position < xDataList.size(); position++) {
            String content = xDataList.get(position);
            //X轴坐标=X轴原点坐标+行宽-文字宽度/2;
            int xStart = (int) (zoomRect.left + (position + 1) * perWidth);
            int yStart = zoomRect.bottom;
//            Log.i(TAG, "--x:" + xStart + "--y:" + yStart + "--content:" + content);
            if (xStart >= xZero && xStart <= chartRect.right) {
                canvas.drawText(content, xStart - paint.measureText(content) / 2, yStart, scaleTextStyle.fillPaint(paint));
                if(isShowXScaleLine()){
                    canvas.drawLine(xStart, yZero, xStart, zoomRect.top, gridStyle.fillPaint(paint));
                }
            }
            Log.i(TAG, "---position:" + position + "--x:" + xStart + "--y:" + yStart + "--perWidth:" + perWidth + "--bottom:" + zoomRect.bottom + "--translateX:" + helper.getTranslateX());
        }
    }


}
