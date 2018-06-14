package demo.selva.com.assessments.service;

import demo.selva.com.assessments.model.BarResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
public interface ApiService {
    String BASE_URL = "https://api.myjson.com/";

    @GET("bins/ipz6h")
    Call<BarResponse> getBarValues();
}
