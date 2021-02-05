package com.catha.mvp.Data.remote;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "f3aa09e2f1d7494e9fded68f30d4e4c1";

    public static Retrofit getRetrofitInstance(Context context) {

        if (retrofit == null) {


            //001-ini untuk menampilkan log dari penggunaan api
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //end of note 001
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("x-api-key", API_KEY)
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);

                        //
                    })
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}