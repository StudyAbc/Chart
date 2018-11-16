package com.jiangfeng.chart.data;

import java.util.Arrays;
import java.util.List;

public class ChartData<T extends ColumnData> {
    /**
     * 表名
     */
    private String chartName;
    /**
     * X轴数据
     */
    private List<String> xDataList;
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

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public List<String> getxDataList() {
        return xDataList;
    }

    public void setxDataList(List<String> xDataList) {
        this.xDataList = xDataList;
    }

    public List<T> getColumnDataList() {
        return columnDataList;
    }

    public void setColumnDataList(List<T> columnDataList) {
        this.columnDataList = columnDataList;
    }

    public ScaleData getScaleData() {
        if(scaleData==null){
            scaleData=new ScaleData();
        }
        return scaleData;
    }

    public void setScaleData(ScaleData scaleData) {
        this.scaleData = scaleData;
    }


}
