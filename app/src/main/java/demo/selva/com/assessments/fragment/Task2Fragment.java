package demo.selva.com.assessments.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.selva.com.assessments.R;
import demo.selva.com.assessments.model.BarResponse;
import demo.selva.com.assessments.service.ApiClient;
import demo.selva.com.assessments.util.BarChartView;
import demo.selva.com.assessments.util.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/13/2018
 */
public class Task2Fragment extends Fragment {
    private BarChartView mBarChartView;
    private TextView mTextLoading;
    private ApiClient mApiClient;

    public static Task2Fragment getInstance() {
        return new Task2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task2, container, false);
        initialiseViews(view);
        initialiseService();
        return view;
    }

    /**
     * This method is used to initialise the service
     * and retrieve, update the bar values into BarChartView from web service
     */
    private void initialiseService() {
        if (NetworkUtils.isNetworkConnected()) {
            if (null == mApiClient) {
                mApiClient = new ApiClient();
            }
            mApiClient.getApiClient().getBarValues().enqueue(new Callback<BarResponse>() {
                @Override
                public void onResponse(@Nullable Call<BarResponse> call, @Nullable Response<BarResponse> response) {
                    if (null != response && response.isSuccessful()) {
                        BarResponse barResponse = response.body();
                        if (null != barResponse && null != barResponse.getGraph()) {
                            ArrayList<BarResponse.BarValues> values =
                                    (ArrayList<BarResponse.BarValues>) barResponse.getGraph();
                            Integer[] array = new Integer[values.size()];
                            for (int i = 0; i < values.size(); i++) {
                                array[Integer.parseInt(values.get(i).getIndex()) - 1]
                                        = Integer.parseInt(values.get(i).getValue());
                            }
                            List<Integer> barValue = Arrays.asList(array);
                            showOrHideBarChartView(true);
                            mBarChartView.setBarData(barValue);
                            mBarChartView.build();
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<BarResponse> call, @Nullable Throwable t) {
                    showOrHideBarChartView(false);
                    mTextLoading.setText(R.string.text_something_went_wrong);
                }
            });
        } else {
            showOrHideBarChartView(false);
            mTextLoading.setText(R.string.text_no_internet_connection);
        }
    }

    /**
     * This method is used to show or hide the bar chart view and loading text view
     *
     * @param isShow boolean true for to show the bar chart view else false
     */
    private void showOrHideBarChartView(boolean isShow) {
        if (isShow) {
            mTextLoading.setVisibility(View.GONE);
            mBarChartView.setVisibility(View.VISIBLE);
        } else {
            mTextLoading.setVisibility(View.VISIBLE);
            mBarChartView.setVisibility(View.GONE);
        }
    }

    /**
     * This method is used to initialise the views
     *
     * @param view the View
     */

    private void initialiseViews(View view) {
        mTextLoading = view.findViewById(R.id.text_loading);
        mBarChartView = view.findViewById(R.id.bar_chart_view);
    }
}
