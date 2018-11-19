package com.jiangfeng.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiangfeng.chart.charts.BarChart;
import com.jiangfeng.chart.charts.LineChart;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.LineData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BarChart mBarChart;
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mBarChart = findViewById(R.id.main_barChart);
            mLineChart = findViewById(R.id.main_lineChart);
            initBarChart(mBarChart);
            initChart(mLineChart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBarChart(BarChart barChart) {
        int size = 10;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10.1 + Math.random() * 100);
        }
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("部门" + i);
        }
//        LineData lineData = new LineData("name", "元", yList);
        ChartData<LineData> chartData = new ChartData("营收报表", xAxisList, yList);
        //图表外边距
        barChart.setPadding(20);
        barChart.setShowXScaleLine(true);
        barChart.setShowYScaleLine(true);
        barChart.setYScaleSize(6);
        barChart.setShowPoint(true);
        barChart.setShowLine(true);
        barChart.getMatrixHelper().setWidthMultiple(2f);
        barChart.setChartData(chartData);
    }

    private void initChart(LineChart lineChart) {
        int size = 15;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10.1 + Math.random() * 100);
        }
//        yList.add(0.0);
//        yList.add(10.0);
//        yList.add(20.0);
//        yList.add(30.0);
//        yList.add(40.0);
//        yList.add(50.0);
//        yList.add(60.0);
//        yList.add(40.0);
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("部门" + i);
        }
//        LineData lineData = new LineData("name", "元", yList);
        ChartData<LineData> chartData = new ChartData("营收报表", xAxisList, yList);
        //图表外边距
        lineChart.setPadding(20);
        lineChart.setShowXScaleLine(true);
        lineChart.setShowYScaleLine(true);
        lineChart.setXScaleSize(10);
        lineChart.setYScaleSize(10);
        lineChart.setZoom(true);
        lineChart.getMatrixHelper().setWidthMultiple(2f);
        lineChart.setLineModel(LineChart.CURVE_MODEL);
        lineChart.setShowPoint(true);
        lineChart.setChartData(chartData);
    }
}
