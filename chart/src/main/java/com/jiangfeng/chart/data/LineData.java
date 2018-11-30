//package com.jiangfeng.chart.data;
//
//import com.jiangfeng.chart.data.style.FontStyle;
//import com.jiangfeng.chart.data.style.LineStyle;
//
//import java.util.List;
//
///**
// * Created by LWH
// * 2018/11/30 9:42
// * 线形图数据
// */
//public class LineData<T> extends ColumnData {
//    /**
//     * 文字样式
//     */
//    private FontStyle valueTextStyle;
//    /**
//     * 刻度样式
//     */
//    private LineStyle xScaleStyle;
//    private LineStyle yScaleStyle;
//
//    public LineData(String name, String unit, int[] colors, List<T> dataList) {
//        super(name, unit, colors, dataList);
//    }
//
//
//    public FontStyle getValueTextStyle() {
//        return valueTextStyle;
//    }
//
//    public void setValueTextStyle(FontStyle valueTextStyle) {
//        this.valueTextStyle = valueTextStyle;
//    }
//
//    public LineStyle getxScaleStyle() {
//        return xScaleStyle;
//    }
//
//    public void setxScaleStyle(LineStyle xScaleStyle) {
//        this.xScaleStyle = xScaleStyle;
//    }
//
//    public LineStyle getyScaleStyle() {
//        return yScaleStyle;
//    }
//
//    public void setyScaleStyle(LineStyle yScaleStyle) {
//        this.yScaleStyle = yScaleStyle;
//    }
//}
