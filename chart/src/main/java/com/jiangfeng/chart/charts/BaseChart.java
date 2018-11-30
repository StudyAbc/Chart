package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnChartChangeListener;
import com.jiangfeng.chart.listener.OnClickColumnListener;
import com.jiangfeng.chart.matrix.MatrixHelper;
import com.jiangfeng.chart.util.DensityUtil;

/**
 * Created by LWH
 * 2018/11/30 15:17
 * 图表父类
 * 测量图表大小，设置图表基础属性,处理手势操作
 */
public abstract class BaseChart<T> extends View implements OnChartChangeListener {
    private final String TAG = BaseChart.class.getName();
    /**
     * 画笔
     */
    protected Paint mPaint;
    /**
     * 图表数据
     */
    protected ChartData<T> mChartData;
    /**
     * 图表区域
     */
    protected Rect mChartRect;
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
    protected MatrixHelper<T> mMatrixHelper;

    protected IProvider<T> iProvider;

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
        iProvider = initProvider();
    }

    /**
     * 初始化内容绘制
     */
    abstract IProvider<T> initProvider();

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
     * 触摸事件
     *
     * @param event 事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mMatrixHelper.handlerTouchEvent(event);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        mMatrixHelper.onDisallowInterceptEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onChartChange(float translateX, float translateY) {
        Log.i(TAG, "--translateX:" + translateX + "--translteY:" + translateY);
        invalidate();
    }

    public void setOnClickColumnListener(OnClickColumnListener<T> onClickColumnListener) {
        mMatrixHelper.setClickColumnListener(onClickColumnListener);
    }

    /**
     * 计算图表区域
     */
    protected void computeChartRect() {
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
