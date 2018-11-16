package com.jiangfeng.chart.data.style;

import android.graphics.Color;
import android.graphics.Paint;

public class FontStyle implements IStyle {
    private float mTextSize = 24f;
    private int mTextColor = Color.parseColor("#000000");
    private float mWidth;

    public FontStyle() {
    }


    public FontStyle setTextSize(float textSize) {
        this.mTextSize = textSize;
        return this;
    }


    public FontStyle setTextColor(int textColor) {
        this.mTextColor = textColor;
        return this;
    }

    public FontStyle setWidth(float width) {
        this.mWidth = width;
        return this;
    }

    @Override
    public Paint fillPaint(Paint paint) {
        paint.setColor(mTextColor);
        paint.setTextSize(mTextSize);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(mWidth);
        return paint;
    }
}
