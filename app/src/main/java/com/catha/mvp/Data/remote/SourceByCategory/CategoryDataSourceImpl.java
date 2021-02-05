package com.catha.mvp.Data.remote.SourceByCategory;

import android.content.Context;
import android.widget.Toast;

import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.RemoteDataInstance;
import com.catha.mvp.ui.sourcebycategory.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDataSourceImpl implements CategoryDataSource {
    private CategoryDataService categoryDataService;
MainActivity mainActivity;
    public CategoryDataSourceImpl(Context context) {
        categoryDataService = RemoteDataInstance.getRetrofitInstance(context).create(CategoryDataService.class);
    }

    @Override
    public void getSourceByCategory(String category, OnGetSourcesByCategoryListener onGetSourcesByCategoryListener) {
        Call<GetSourcesDataBean> call = categoryDataService.getSourcesByCategory(category);
        call.enqueue(new Callback<GetSourcesDataBean>() {
            @Override
            public void onResponse(Call<GetSourcesDataBean> call, Response<GetSourcesDataBean> response) {
                try {
                    int code = response.code();
                    if (code == 200) {
                        GetSourcesDataBean getSourcesDataBean = response.body();
                        if (getSourcesDataBean.getStatus().equals("ok")) {

                            onGetSourcesByCategoryListener.onSuccess(getSourcesDataBean);
                        } else {
                            onGetSourcesByCategoryListener.onFailed("Failed to get sources by category");
//                            Toast.makeText(mainActivity, "Failed to get sources by category", Toast.LENGTH_SHORT).show();
                        }

                    } else if (code == 400) {
                        onGetSourcesByCategoryListener.onFailed("Bad request");
//                        Toast.makeText(mainActivity, "Bad request", Toast.LENGTH_SHORT).show();

                    } else if (code == 401) {
                        onGetSourcesByCategoryListener.onFailed("Unauthorized");
//                        Toast.makeText(mainActivity, "Unauthorized", Toast.LENGTH_SHORT).show();

                    } else if (code == 429) {
                        onGetSourcesByCategoryListener.onFailed("Too Many Request");
//                        Toast.makeText(mainActivity, "Too Many request", Toast.LENGTH_SHORT).show();

                    } else if (code == 500) {
                        onGetSourcesByCategoryListener.onFailed("Server Error");
//                        Toast.makeText(mainActivity, "Server Error", Toast.LENGTH_SHORT).show();

                    } else {
                        onGetSourcesByCategoryListener.onFailed("Error status code");
//                        Toast.makeText(mainActivity, "Error status code", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    onGetSourcesByCategoryListener.onFailed(toString());
//                    Toast.makeText(mainActivity, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSourcesDataBean> call, Throwable t) {
                onGetSourcesByCategoryListener.onFailed(t.toString());
//                Toast.makeText(mainActivity, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
