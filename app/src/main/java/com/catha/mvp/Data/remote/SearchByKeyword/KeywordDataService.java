package com.catha.mvp.Data.remote.SearchByKeyword;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KeywordDataService {
    @GET("everything")
    Call<GetKeywordDataBean> getByKeyword(@Query("q") String keyword);
}
