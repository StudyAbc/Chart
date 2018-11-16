package com.jiangfeng.chart.listener;

/**
 * Created by LWH
 * 2018/11/16 15:24
 * 图表变化监听
 */
public interface OnChartChangeListener {
    /**
     * 图表变化监听
     *
     * @param translateX X轴移动距离
     * @param translateY Y轴移动距离
     */
    void onChartChange(float translateX, float translateY);
}
