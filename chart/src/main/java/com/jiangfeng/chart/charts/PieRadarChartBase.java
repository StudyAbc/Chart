package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnChartChangeListener;
import com.jiangfeng.chart.matrix.MatrixHelper;
import com.jiangfeng.chart.util.DensityUtil;

/**
 * Created by LWH
 * 2018/11/30 13:48
 * 饼图和雷达图的基类
 */
public abstract class PieRadarChartBase<T> extends BaseChart implements OnChartChangeListener {

    /**
     * 显示坐标点
     */
    private boolean mShowPoint;
    /**
     * 显示坐标点的值
     */
    private boolean mShowPointValue;
    /**
     * 手势辅助类
     */
    private MatrixHelper<T> mMatrixHelper;

    public PieRadarChartBase(Context context) {
        super(context);
        init(context);
    }

    public PieRadarChartBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PieRadarChartBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mChartData != null) {
            computeChartRect();
            //绘制坐标点，和图表
            drawProvider(canvas, mPaint, mChartRect, mChartData);
        }
    }

    @Override
    public void onChartChange(float translateX, float translateY) {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        mMatrixHelper.onDisallowInterceptEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    /**
     * 绘制坐标点，线形图或柱状图
     *
     * @param canvas    画布
     * @param paint     画笔
     * @param chartRect 图表范围
     * @param chartData 图表数据
     */
    abstract void drawProvider(Canvas canvas, Paint paint, Rect chartRect, ChartData<T> chartData);


    /**
     * 尺寸变化
     *
     * @param w    当前宽度
     * @param h    当前高度
     * @param oldw 之前的宽度
     * @param oldh 之前的高度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mChartWidth = w;
        mChartHeight = h;
    }

    /**
     * 计算图表区域
     */
    private void computeChartRect() {
        mChartRect.left = mPadding;
        mChartRect.top = mPadding;
        mChartRect.right = mChartWidth - mPadding;
        mChartRect.bottom = mChartHeight - mPadding;
    }

    /**
     * 设置图表内边距 dp
     *
     * @param padding 内边距
     */
    public void setPadding(int padding) {
        this.mPadding = DensityUtil.dp2px(mContext, padding);
    }
}
