package com.jiangfeng.chart.data.style;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;

/**
 * 线样式
 */
public class LineStyle implements IStyle {
    private float width = 2f;
    private int color = Color.parseColor("#000000");
    private PathEffect effect = new PathEffect();
    private Paint.Style style=Paint.Style.STROKE;

    public LineStyle() {
    }

    public Paint.Style getStyle() {
        return style;
    }

    public LineStyle setStyle(Paint.Style style) {
        this.style = style;
        return this;
    }

    public LineStyle setWidth(float width) {
        this.width = width;
        return this;
    }

    public LineStyle setColor(int color) {
        this.color = color;
        return this;
    }

    public LineStyle setEffect(PathEffect effect) {
        this.effect = effect;
        return this;
    }

    @Override
    public Paint fillPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setStyle(style);
        paint.setPathEffect(effect);
        return paint;
    }
}
