package demo.selva.com.assessments.service;

import java.util.concurrent.TimeUnit;

import demo.selva.com.assessments.AssessmentApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
public class ApiClient {
    private Retrofit mRetrofit;

    /**
     * Empty constructor
     */
    public ApiClient() {
    }

    /**
     * This method is used to initialise the retrofit instance
     *
     * @return ApiService the ApiService
     */
    public ApiService getApiClient() {
        if (null == mRetrofit) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);

            int cacheSize = 10 * 1024 * 1024;//10Mb
            Cache cache = new Cache(AssessmentApplication.getInstance().getCacheDir(), cacheSize);
            builder.cache(cache);
            OkHttpClient okHttpClient = builder.build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit.create(ApiService.class);
    }
}
