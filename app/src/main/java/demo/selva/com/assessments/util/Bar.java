package demo.selva.com.assessments.util;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/13/2018
 */
public class Bar extends ProgressBar {
    public Bar(Context context) {
        super(context);
    }

    public Bar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Bar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(h, w, oldH, oldW);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);
    }
}
