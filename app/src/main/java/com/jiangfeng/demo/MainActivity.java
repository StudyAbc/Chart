package com.jiangfeng.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jiangfeng.chart.charts.BarChart;
import com.jiangfeng.chart.charts.LineChart;
import com.jiangfeng.chart.charts.PieChart;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnClickColumnListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BarChart mBarChart;
    private LineChart mLineChart;
    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mBarChart = findViewById(R.id.main_barChart);
            mLineChart = findViewById(R.id.main_lineChart);
            mPieChart = findViewById(R.id.main_pieChart);
            initBarChart(mBarChart);
            initChart(mLineChart);
            initPieChart(mPieChart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBarChart(BarChart barChart) {
        int size = 20;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10.1 + Math.random() * 100);
        }
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("部门" + i);
        }
        final ChartData<Double> chartData = new ChartData<>("营收报表", xAxisList, yList);
        //图表外边距
        barChart.setPadding(20);
        barChart.setShowXScaleLine(true);
        barChart.setShowYScaleLine(true);
        barChart.setXScaleSize(10);
        barChart.setYScaleSize(6);
        barChart.setShowPointValue(true);
        barChart.setShowLine(true);
        barChart.setChartData(chartData);
        barChart.setOnClickColumnListener(new OnClickColumnListener<Double>() {
            @Override
            public void onClickColumn(int position, String columnName, Double columnData) {
                Toast.makeText(getBaseContext(), "position:" + position + "name:" + columnName + "columnData:" + columnData, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initChart(LineChart lineChart) {
        int size = 15;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10.1 + Math.random() * 100);
        }
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("部门" + i);
        }
        ChartData<Double> chartData = new ChartData<>("营收报表", xAxisList, yList);
        //图表外边距
        lineChart.setPadding(25);
        lineChart.setShowXScaleLine(true);
        lineChart.setShowYScaleLine(true);
        lineChart.setXScaleSize(5);
        lineChart.setYScaleSize(10);
        lineChart.setZoom(true);
        lineChart.setLineModel(LineChart.CURVE_MODEL);
        lineChart.setShowPointValue(true);
        lineChart.setChartData(chartData);
    }

    private void initPieChart(PieChart pieChart) {
        int size = 15;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10.1 + Math.random() * 100);
        }
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("部门" + i);
        }
        ChartData<Double> chartData = new ChartData<>("营收报表", xAxisList, yList);
        pieChart.setChartData(chartData);
    }
}
