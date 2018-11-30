package com.jiangfeng.chart.component;

import android.graphics.Paint;

import com.jiangfeng.chart.data.format.IFormat;
import com.jiangfeng.chart.data.style.FontStyle;
import com.jiangfeng.chart.data.style.LineStyle;

/**
 * 轴线父类
 */
public abstract class AxisBase<T> implements IAxis<T> {
    /**
     * 轴线样式
     */
    private LineStyle axisStyle;
    /**
     * 网格线样式
     */
    private LineStyle gridStyle;
    /**
     * 刻度样式
     */
    private FontStyle scaleTextStyle;
    /**
     * 是否显示X轴刻度线
     */
    private boolean showXScaleLine;
    /**
     * 是否显示Y轴刻度线
     */
    private boolean showYScaleLine;
    /**
     * X轴刻度条数，即显示列条数
     */
    private int xScaleSize = 5;
    /**
     * Y轴刻度条数
     */
    private int yScaleSize = 2;
    /**
     * X轴原点坐标
     */
    protected float xZero;
    /**
     * Y轴原点坐标
     */
    protected float yZero;

    private IFormat<T> format;

    public AxisBase() {
        axisStyle = new LineStyle();
        gridStyle = new LineStyle();
        scaleTextStyle = new FontStyle();
    }

    public LineStyle getAxisStyle() {
        return axisStyle;
    }

    public void setAxisStyle(LineStyle axisStyle) {
        this.axisStyle = axisStyle;
    }

    public LineStyle getGridStyle() {
        return gridStyle;
    }

    public void setGridStyle(LineStyle gridStyle) {
        this.gridStyle = gridStyle;
    }

    public FontStyle getScaleTextStyle() {
        return scaleTextStyle;
    }

    public void setScaleTextStyle(FontStyle scaleTextStyle) {
        this.scaleTextStyle = scaleTextStyle;
    }


    public boolean isShowXScaleLine() {
        return showXScaleLine;
    }

    public void setShowXScaleLine(boolean showXScaleLine) {
        this.showXScaleLine = showXScaleLine;
    }

    public boolean isShowYScaleLine() {
        return showYScaleLine;
    }

    public void setShowYScaleLine(boolean showYScaleLine) {
        this.showYScaleLine = showYScaleLine;
    }

    public int getyScaleSize() {
        return yScaleSize;
    }

    public void setyScaleSize(int yScaleSize) {
        this.yScaleSize = yScaleSize;
    }

    public int getxScaleSize() {
        return xScaleSize;
    }

    public void setxScaleSize(int xScaleSize) {
        this.xScaleSize = xScaleSize;
    }

    public float getxZero() {
        return xZero;
    }

    public void setxZero(float xZero) {
        this.xZero = xZero;
    }

    public float getyZero() {
        return yZero;
    }

    public void setyZero(float yZero) {
        this.yZero = yZero;
    }

    @Override
    public IFormat<T> getFormat() {
        return format;
    }

    @Override
    public void setFormat(IFormat<T> format) {
        this.format = format;
    }

    @Override
    public float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }


}
