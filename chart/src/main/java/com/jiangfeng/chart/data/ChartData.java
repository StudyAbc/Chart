package com.jiangfeng.chart.data;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LWH
 * 2018/11/30 9:56
 * 图表数据
 */
public class ChartData<T> {
    /**
     * 表名
     */
    private String chartName;
    /**
     * X轴数据
     */
    private List<String> xDataList;
    /**
     * 列的颜色
     */
    private int[] colors = new int[]{Color.parseColor("#a035c7"),
            Color.parseColor("#3cc735"),
            Color.parseColor("#c9276b"),
            Color.parseColor("#c48224"),
            Color.parseColor("#3185c9"),
            Color.parseColor("#1dd2c0")};
    /**
     * 列的值数据
     */
    private List<T> columnDataList;
    /**
     * 坐标轴刻度
     */
    private ScaleData scaleData;

    public ChartData(String chartName, List<String> xDataList, List<T> columnDataList) {
        this.chartName = chartName;
        this.xDataList = xDataList;
        this.columnDataList = columnDataList;
    }

    public ChartData(String chartName, List<String> xDataList, T... columnData) {
        this.chartName = chartName;
        this.xDataList = xDataList;
        this.columnDataList = Arrays.asList(columnData);
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public List<String> getxDataList() {
        return xDataList;
    }

    public void setxDataList(ArrayList<String> xDataList) {
        this.xDataList = xDataList;
    }

    public List<T> getColumnDataList() {
        return columnDataList;
    }

    public void setColumnDataList(List<T> columnDataList) {
        this.columnDataList = columnDataList;
    }

    public ScaleData getScaleData() {
        if (scaleData == null) {
            scaleData = new ScaleData();
        }
        return scaleData;
    }

    public void setScaleData(ScaleData scaleData) {
        this.scaleData = scaleData;
    }


}
