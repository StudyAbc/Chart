package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.matrix.MatrixHelper;

public class BaseChart<T> extends View {
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 图表数据
     */
    private ChartData<T> mChartData;
    /**
     * 图表区域
     */
    private Rect mChartRect;
    /**
     * 图表宽度
     */
    private int mChartWidth;
    /**
     * 图表高度
     */
    private int mChartHeight;
    /**
     * 图表内边距(像素)
     */
    private int mPadding = 50;
    private Context mContext;
    /**
     * 手势辅助类
     */
    private MatrixHelper<T> mMatrixHelper;

    public BaseChart(Context context) {
        super(context);
        init(context);
    }

    public BaseChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mChartRect = new Rect();
        mContext = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mMatrixHelper = new MatrixHelper<T>(context);
        mMatrixHelper.setOnChartChangeListener(this);
    }
}
