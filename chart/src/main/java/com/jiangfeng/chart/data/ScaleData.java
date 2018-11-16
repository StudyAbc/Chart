package com.jiangfeng.chart.data;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LWH
 * 2018/11/13 15:50
 * 刻度
 */
public class ScaleData {
    /**
     * X轴最小值
     */
    private double minX;
    /**
     * X轴最大值
     */
    private double maxX;
    /**
     * Y轴最小值
     */
    private double minY;
    /**
     * Y轴最大值
     */
    private double maxY;
    /**
     * 刻度区域
     */
    private Rect rect;
    private List<Double> scaleList;

    public Rect getRect() {
        if (rect == null) {
            rect = new Rect();
        }
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public List<Double> getScaleList() {
        if (scaleList == null) {
            scaleList = new ArrayList<>();
        }
        return scaleList;
    }

    public void setScaleList(List<Double> scaleList) {
        this.scaleList = scaleList;
    }
}
