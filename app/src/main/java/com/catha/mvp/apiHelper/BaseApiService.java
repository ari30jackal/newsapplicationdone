package com.catha.mvp.apiHelper;


import com.catha.mvp.keywordresponse.KeywordResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface BaseApiService {

    @GET
    Call<KeywordResponse> getbykeyword(@Url String url);

}
