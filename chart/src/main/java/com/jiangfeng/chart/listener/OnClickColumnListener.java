package com.jiangfeng.chart.listener;

/**
 * Created by LWH
 * 2018/11/29 15:55
 * 柱图点击事件
 */
public interface OnClickColumnListener<T> {
    /**
     * 列的点击事件
     *
     * @param position   柱图索引
     * @param columnName 列名
     * @param columnData 列数据
     */
    void onClickColumn(int position, String columnName,  T columnData);
}
