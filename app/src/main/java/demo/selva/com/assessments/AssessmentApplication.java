package demo.selva.com.assessments;

import android.app.Application;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
public class AssessmentApplication extends Application {
    private static AssessmentApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static AssessmentApplication getInstance() {
        return mInstance;
    }
}
