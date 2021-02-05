package com.catha.mvp.Data.remote.NewsBySource;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsDataService {

    @GET("everything")
    Call<GetNewsBySourceDataBean> getNewsBySource(@Query("sources") String source);
}
