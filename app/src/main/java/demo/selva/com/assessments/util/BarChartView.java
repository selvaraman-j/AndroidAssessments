package demo.selva.com.assessments.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import demo.selva.com.assessments.R;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/13/2018
 */
public class BarChartView extends FrameLayout {
    private final Context mContext;
    private int mBarWidth;
    private int mBarHeight;
    private int mMaxValue;
    private int mBarRadius;
    private int mProgressColor;
    private int mBackgroundColor;
    private List<Integer> mBarData = new ArrayList<>();

    /**
     * Constructor is used to initialise the bar chart view attribute values
     *
     * @param context the activity context
     * @param attrs   the bar chart view AttributeSet
     */
    public BarChartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setAttr(attrs, 0);
    }

    /**
     * Constructor is used to initialise the bar chart view attribute values
     *
     * @param context      the activity context
     * @param attrs        the bar chart view AttributeSet
     * @param defStyleAttr int the default style attribute
     */
    public BarChartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setAttr(attrs, defStyleAttr);
    }

    /**
     * This method is used to assign the attribute values
     *
     * @param attributeSet the AttributeSet
     * @param defStyleAttr int the default style attribute
     */
    private void setAttr(AttributeSet attributeSet, int defStyleAttr) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet
                , R.styleable.BarChartView, defStyleAttr, 0);
        mBarWidth = typedArray.getDimensionPixelSize(R.styleable.BarChartView_barWidth, 0);
        mBarHeight = typedArray.getDimensionPixelSize(R.styleable.BarChartView_barHeight, 0);
        mMaxValue = typedArray.getInt(R.styleable.BarChartView_maxValue, 5);
        mBarRadius = typedArray.getDimensionPixelSize(R.styleable.BarChartView_barRadius, 0);
        mBackgroundColor = typedArray.getColor(R.styleable.BarChartView_backgroundColor
                , ContextCompat.getColor(mContext, android.R.color.holo_blue_light));
        mProgressColor = typedArray.getColor(R.styleable.BarChartView_progressColor
                , ContextCompat.getColor(mContext, android.R.color.white));
        typedArray.recycle();
    }

    /**
     * This method is used to build the bar chart view
     */
    public void build() {
        removeAllViews();
        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(mBackgroundColor);
        addView(linearLayout);

        for (int value : mBarData) {
            linearLayout.addView(getBar(value));
        }
    }

    /**
     * This method is used to set the background of the bar chart view
     *
     * @param backgroundColor int the background color
     */
    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
    }

    /**
     * This method is used to set the bar values
     *
     * @param barData List<Integer> the list containing bar values
     */
    public void setBarData(List<Integer> barData) {
        this.mBarData = barData;
    }

    /**
     * This method is used to set the progress bar maximum value
     *
     * @param maxValue int the progress bar max value
     */
    public void setMaxValue(int maxValue) {
        this.mMaxValue = maxValue;
    }

    /**
     * This method is used to set the progress bar radius
     *
     * @param radius int the radius of the progress bar
     */
    public void setBarRadius(int radius) {
        this.mBarRadius = radius;
    }

    /**
     * This method is used to create the single bar view
     *
     * @param value int the bar value
     * @return LinearLayout the bar view
     */
    private LinearLayout getBar(final int value) {

        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LayoutParams.MATCH_PARENT, 1f);
        linearLayout.setPadding(0, 0, 0, 15);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

        //Adding bar
        Bar bar = new Bar(mContext, null, android.R.attr.progressBarStyleHorizontal);
        bar.setProgress(value);
        bar.setVisibility(View.VISIBLE);
        bar.setIndeterminate(false);
        bar.setProgressDrawable(ContextCompat.getDrawable(mContext, R.drawable.progress_bar_shape));
        //bar animation
        BarAnimation barAnimation = new BarAnimation(bar, 0, value);
        barAnimation.setDuration(100);
        bar.setAnimation(barAnimation);

        LayerDrawable layerDrawable = (LayerDrawable) bar.getProgressDrawable();
        layerDrawable.mutate();
        ScaleDrawable scaleDrawable = (ScaleDrawable) layerDrawable.getDrawable(0);
        GradientDrawable progressLayer = (GradientDrawable) scaleDrawable.getDrawable();

        //set the bar color and radius
        if (progressLayer != null) {
            progressLayer.setColor(mProgressColor);
            float radius[] = new float[]{0f, 0f, mBarRadius, mBarRadius, mBarRadius, mBarRadius, 0f, 0f};
            progressLayer.setCornerRadii(radius);
        }

        LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(mBarWidth, mBarHeight);
        bar.setLayoutParams(barParams);
        bar.setMax(mMaxValue);
        linearLayout.addView(bar);
        return linearLayout;
    }
}
