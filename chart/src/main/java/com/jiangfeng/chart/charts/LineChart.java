package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.LineData;
import com.jiangfeng.chart.data.style.FontStyle;
import com.jiangfeng.chart.data.style.LineStyle;

import java.util.List;

/**
 * Created by LWH
 * 2018/11/16 10:17
 * 折线图和曲线图
 */
public class LineChart extends BarLineChartBase<LineData> {
    private final String TAG = LineChart.class.getName();
    /**
     * 折线图
     */
    public final static int LINE_MODEL = 1;
    /**
     * 曲线图
     */
    public final static int CURVE_MODEL = 2;
    /**
     * 线模式
     */
    private int lineModel;
    /**
     * 曲线曲率
     */
    private float curvatrue = 0.35f;

    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void drawProviderValue(Canvas canvas, Paint paint, Rect chartRect, ChartData chartData) {
        //Y轴数据
        List<Double> columnDataList = chartData.getColumnDataList();
        //X轴显示列数
        int xSize = chartData.getxDataList().size();
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
            //绘制折线和曲线
            if (i < columnDataList.size() - 1) {
                //下一个坐标点
                int xNext = (int) (mXAxis.getxZero() + (startIndex + 1) * perWidth);
                int yNext = (int) (mXAxis.getyZero() - mYAxis.getYHeightByVlaue(chartData, chartRect, columnDataList.get(i + 1)));
                if (lineModel == LINE_MODEL) {
                    canvas.drawLine(xCurrent, yCurrent, xNext, yNext, lineStyle.fillPaint(paint));
                } else if (lineModel == CURVE_MODEL) {
                    //中心点
                    int xMid = (xCurrent + xNext) / 2;
                    Path path = new Path();
                    path.moveTo(xCurrent, yCurrent);
                    path.cubicTo(xMid, yCurrent, xMid, yNext, xNext, yNext);
                    canvas.drawPath(path, lineStyle.fillPaint(paint));
                } else {
                    Log.i(LineChart.class.getName(), "lineModel=" + lineModel + " is unknown");
                }
            }

        }
    }

    public void setLineModel(int lineModel) {
        this.lineModel = lineModel;

    }


}
