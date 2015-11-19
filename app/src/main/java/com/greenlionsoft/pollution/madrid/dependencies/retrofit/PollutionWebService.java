package com.greenlionsoft.pollution.madrid.dependencies.retrofit;

import entities.PollutionData;
import retrofit.Callback;
import retrofit.http.GET;

public interface PollutionWebService {

    @GET("/v1.1/data")
    void getPollutionData(Callback<PollutionData> cb);

}
