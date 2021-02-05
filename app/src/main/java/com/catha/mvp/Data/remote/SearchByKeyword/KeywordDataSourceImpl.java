package com.catha.mvp.Data.remote.SearchByKeyword;

import android.content.Context;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.RemoteDataInstance;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataService;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.ui.sourcebycategory.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeywordDataSourceImpl implements KeywordDataSource{

    private KeywordDataService keywordDataService;
    MainActivity mainActivity;
    public KeywordDataSourceImpl(Context context) {
        keywordDataService = RemoteDataInstance.getRetrofitInstance(context).create(KeywordDataService.class);
    }

    @Override
    public void getByKeyword(String keyword, KeywordDataSource.OnGetByKeywordListener onGetByKeywordListener) {
        Call<GetKeywordDataBean> call = keywordDataService.getByKeyword(keyword);
        call.enqueue(new Callback<GetKeywordDataBean>() {
            @Override
            public void onResponse(Call<GetKeywordDataBean> call, Response<GetKeywordDataBean> response) {
                try {
                    int code = response.code();
                    if (code == 200) {
                        GetKeywordDataBean getKeywordDataBean = response.body();
                        if (getKeywordDataBean.getStatus().equals("ok")) {

                            onGetByKeywordListener.onSuccess(getKeywordDataBean);
                        } else {
                            onGetByKeywordListener.onFailed("Failed to get sources by category");
//                            Toast.makeText(mainActivity, "Failed to get sources by category", Toast.LENGTH_SHORT).show();
                        }

                    } else if (code == 400) {
                        onGetByKeywordListener.onFailed("Bad request");
//                        Toast.makeText(mainActivity, "Bad request", Toast.LENGTH_SHORT).show();

                    } else if (code == 401) {
                        onGetByKeywordListener.onFailed("Unauthorized");
//                        Toast.makeText(mainActivity, "Unauthorized", Toast.LENGTH_SHORT).show();

                    } else if (code == 429) {
                        onGetByKeywordListener.onFailed("Too Many Request");
//                        Toast.makeText(mainActivity, "Too Many request", Toast.LENGTH_SHORT).show();

                    } else if (code == 500) {
                        onGetByKeywordListener.onFailed("Server Error");
//                        Toast.makeText(mainActivity, "Server Error", Toast.LENGTH_SHORT).show();

                    } else {
                        onGetByKeywordListener.onFailed("Error status code");
//                        Toast.makeText(mainActivity, "Error status code", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    onGetByKeywordListener.onFailed(toString());
//                    Toast.makeText(mainActivity, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetKeywordDataBean> call, Throwable t) {
                onGetByKeywordListener.onFailed(t.toString());
//                Toast.makeText(mainActivity, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
