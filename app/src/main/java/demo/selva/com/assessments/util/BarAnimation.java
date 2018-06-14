package demo.selva.com.assessments.util;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
public class BarAnimation extends Animation {
    private final ProgressBar mProgressBar;
    private final float mFrom;
    private final float mTo;

    /**
     * Constructor is to initialise the values
     *
     * @param progressBar the Progressbar
     * @param from        the animation start value
     * @param to          the animation end value
     */
    BarAnimation(ProgressBar progressBar, float from, float to) {
        super();
        this.mProgressBar = progressBar;
        this.mFrom = from;
        this.mTo = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = mFrom + (mTo - mFrom) * interpolatedTime;
        mProgressBar.setProgress((int) value);
    }
}
