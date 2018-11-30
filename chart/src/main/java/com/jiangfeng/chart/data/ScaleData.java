package com.jiangfeng.chart.data;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private Map<Integer, Rect> scaleRectMap = new TreeMap<>();

    public Rect getRect() {
        if (rect == null) {
            rect = new Rect();
        }
        return rect;
    }

    public Rect getOffsetRect(Rect rect, Rect offsetRect) {
        rect.left = rect.left + offsetRect.left;
        rect.right = rect.right - offsetRect.right;
        rect.top = rect.top + offsetRect.top;
        rect.bottom = rect.bottom - offsetRect.bottom;
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

    public Map<Integer, Rect> getScaleRectMap() {
        return scaleRectMap;
    }

    public void setScaleRectMap(Map<Integer, Rect> scaleRectMap) {
        this.scaleRectMap = scaleRectMap;
    }
}
