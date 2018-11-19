package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.style.FontStyle;
import com.jiangfeng.chart.data.style.LineStyle;

import java.util.List;

/**
 * 柱状图
 */
public class BarChart extends BarLineChartBase {
    private final String TAG = BarChart.class.getName();
    /**
     * 显示曲线
     */
    private boolean showLine;

    public BarChart(Context context) {
        super(context);
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void drawProviderValue(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        //Y轴数据
        List<Double> columnDataList = chartData.getColumnDataList();
        //X轴显示列数
        int xSize = mXAxis.getxScaleSize();
        //X轴单位宽度
        double perWidth = (chartRect.right - chartRect.left) / (mXAxis.isLine() ? xSize - 1 : xSize);
        //点的样式
        FontStyle pointStyle = new FontStyle();
        pointStyle.setTextColor(Color.RED).setWidth(10f);
        float textHeight = mXAxis.getTextHeight(pointStyle.fillPaint(paint));
        //折线的样式
        LineStyle lineStyle = new LineStyle();
        lineStyle.setColor(Color.GREEN).setWidth(1f);
        for (int i = 0; i < columnDataList.size(); i++) {
            //从第1列开始绘制坐标点
            int startIndex = i + 1;
            int xCurrent = (int) (mXAxis.getxZero() + startIndex * perWidth);
            //数据Y坐标
            int yCurrent = (int) (mXAxis.getyZero() - mYAxis.getYHeightByVlaue(chartData, chartRect, columnDataList.get(i)));
            //绘制数值位置
            canvas.drawPoint(xCurrent, yCurrent, pointStyle.fillPaint(paint));
//            mYAxis.setFormat(new IFormat<Double>() {
//                @Override
//                public String format(Double aDouble) {
//                    return null;
//                }
//            });
            //绘制文本
            if (isShowPoint()) {
                String content = mYAxis.formatData(columnDataList.get(i));
                pointStyle.setTextSize(24);
                //X坐标=坐标点-文本宽度/2；Y坐标=坐标点-文本高度/2；
                canvas.drawText(content, xCurrent - pointStyle.fillPaint(paint).measureText(content) / 2, yCurrent - textHeight / 2, pointStyle.fillPaint(paint));
            }
            //绘制柱图
            if (i < columnDataList.size() - 1) {
                lineStyle.setStyle(Paint.Style.FILL).setColor(Color.GREEN);
                Rect rect = new Rect();
                rect.left = (int) (xCurrent - perWidth / 3);
                rect.top = yCurrent;
                rect.right = (int) (xCurrent + perWidth / 3);
                rect.bottom = (int) mXAxis.getyZero();
                canvas.drawRect(rect, lineStyle.fillPaint(paint));

            }
            //绘制折线和曲线
            if (showLine) {
                if (i < columnDataList.size() - 1) {
                    lineStyle.setStyle(Paint.Style.STROKE).setColor(Color.BLUE);
                    //下一个坐标点
                    int xNext = (int) (mXAxis.getxZero() + (startIndex + 1) * perWidth);
                    int yNext = (int) (mXAxis.getyZero() - mYAxis.getYHeightByVlaue(chartData, chartRect, columnDataList.get(i + 1)));
                    //中心点
                    int xMid = (xCurrent + xNext) / 2;
                    Path path = new Path();
                    path.moveTo(xCurrent, yCurrent);
                    path.cubicTo(xMid, yCurrent, xMid, yNext, xNext, yNext);
                    canvas.drawPath(path, lineStyle.fillPaint(paint));
                }
            }
        }
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
    }
}
