package com.jiangfeng.chart.component;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.jiangfeng.chart.data.ChartData;
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
     * @param rect      图表范围
     * @param chartData 图表数据
     */
    @Override
    public void drawAxis(Canvas canvas, Paint paint, Rect rect, ChartData chartData) {
        float textHeight = getTextHeight(getScaleTextStyle().setTextSize(36).fillPaint(paint));
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
    public void drawScaleText(Canvas canvas, Paint paint, Rect chartRect, MatrixHelper helper, ChartData chartData) {
        Rect zoomRect = helper.getZoomRect(chartRect);
        zoomRect.top = chartRect.top;
        zoomRect.bottom = chartRect.bottom;
        drawScaleText(canvas, paint, zoomRect, chartData);
    }

    private void drawScaleText(Canvas canvas, Paint paint, Rect zoomRect, ChartData chartData) {
        List<String> xDataList = chartData.getxDataList();
        int xSize = getxScaleSize();
        double perWidth = (zoomRect.right - zoomRect.left) / (isLine() ? xSize - 1 : xSize);
        for (int position = 0; position < xDataList.size(); position++) {
            String content = xDataList.get(position);
            //X轴坐标=X轴原点坐标+行宽-文字宽度/2;
            int xStart = (int) ((zoomRect.left + (position + 1) * perWidth) - paint.measureText(content) / 2);
            int yStart = zoomRect.bottom;
//            Log.i(TAG, "--x:" + xStart + "--y:" + yStart + "--content:" + content);
            canvas.drawText(content, xStart, yStart, getScaleTextStyle().fillPaint(paint));
        }
    }

    @Override
    public void drawScaleLine(Canvas canvas, Paint paint, Rect chartRect, MatrixHelper helper, ChartData chartData) {
        Rect zoomRect = helper.getZoomRect(chartRect);
        zoomRect.top = chartRect.top;
        zoomRect.bottom = chartRect.bottom;
        drawScaleLine(canvas, paint, zoomRect, chartData);
    }

    private void drawScaleLine(Canvas canvas, Paint paint, Rect zoomRect, ChartData chartData) {
        int xSize = getxScaleSize();
        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#d8631b"));
        double perWidth = (zoomRect.right - zoomRect.left) / (isLine() ? xSize - 1 : xSize);
        for (int position = 1; position < xSize; position++) {
            int xStart = (int) (zoomRect.left + position * perWidth);
            paint.setPathEffect(new DashPathEffect(new float[]{1, 2, 4, 8}, 0));
            if (position == 1) {
                Log.i(TAG, "--x:" + xStart + "--y:" + yZero + "--right:" + xStart + "--bottom:" + zoomRect.top);
            }
            canvas.drawLine(xStart, yZero, xStart, zoomRect.top, paint);
        }
    }


}
