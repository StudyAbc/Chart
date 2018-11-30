package com.jiangfeng.chart.charts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by LWH
 * 2018/11/30 12:01
 * 饼图
 */
public class PieChart extends PieRadarChartBase<Double> {
    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
