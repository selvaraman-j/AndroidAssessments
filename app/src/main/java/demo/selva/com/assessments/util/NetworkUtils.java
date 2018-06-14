package demo.selva.com.assessments.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import demo.selva.com.assessments.AssessmentApplication;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
public class NetworkUtils {
    /**
     * Method is to check whether the internet connection is available or not
     *
     * @return true if device is connected to internet otherwise false
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) AssessmentApplication
                .getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (null != connectivityManager) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return null != activeNetworkInfo && activeNetworkInfo.isConnected();
    }
}
