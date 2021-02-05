package com.catha.mvp.Data.remote.SourceByCategory;

import com.catha.mvp.Data.model.GetSourcesDataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryDataService {

    @GET("sources")
    Call<GetSourcesDataBean> getSourcesByCategory(@Query("category") String category);
}
