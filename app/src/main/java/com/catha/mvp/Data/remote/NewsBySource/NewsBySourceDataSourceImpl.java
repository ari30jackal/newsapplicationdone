package com.catha.mvp.Data.remote.NewsBySource;

import android.content.Context;
import android.widget.Toast;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;
import com.catha.mvp.Data.remote.RemoteDataInstance;
import com.catha.mvp.ui.newsbysource.NewsBySourceActivity;
import com.catha.mvp.ui.sourcebycategory.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsBySourceDataSourceImpl implements NewsBySourceDataSource {
    private NewsDataService newsDataService;
NewsBySourceActivity newsBySourceActivity;
    public NewsBySourceDataSourceImpl(Context context) {
        newsDataService = RemoteDataInstance.getRetrofitInstance(context).create(NewsDataService.class);
    }

    @Override
    public void getNewsBySource(String source, NewsBySourceDataSource.OnGetNewsBySourceListener onGetNewsBySourceListener) {
        Call<GetNewsBySourceDataBean> call = newsDataService.getNewsBySource(source);
        call.enqueue(new Callback<GetNewsBySourceDataBean>() {
            @Override
            public void onResponse(Call<GetNewsBySourceDataBean> call, Response<GetNewsBySourceDataBean> response) {
                try {
                    int code = response.code();
                    if (code == 200) {
                        GetNewsBySourceDataBean getNewsBySourceDataBean = response.body();
                        if (getNewsBySourceDataBean.getStatus().equals("ok")) {

                            onGetNewsBySourceListener.onSuccess(getNewsBySourceDataBean);
                        } else {
                            onGetNewsBySourceListener.onFailed("Failed to get sources by category");
//                            Toast.makeText(newsBySourceActivity, "Failed to get sources by category", Toast.LENGTH_SHORT).show();
                        }

                    } else if (code == 400) {
                        onGetNewsBySourceListener.onFailed("Bad request");
//                        Toast.makeText(newsBySourceActivity, "Bad request", Toast.LENGTH_SHORT).show();

                    } else if (code == 401) {
                        onGetNewsBySourceListener.onFailed("Unauthorized");
//                        Toast.makeText(newsBySourceActivity, "Unauthorized", Toast.LENGTH_SHORT).show();

                    } else if (code == 429) {
                        onGetNewsBySourceListener.onFailed("Too Many Request");
//                        Toast.makeText(newsBySourceActivity, "Too Many Request", Toast.LENGTH_SHORT).show();

                    } else if (code == 500) {
                        onGetNewsBySourceListener.onFailed("Server Error");
//                        Toast.makeText(newsBySourceActivity, "Server Error", Toast.LENGTH_SHORT).show();

                    } else {
                        onGetNewsBySourceListener.onFailed("Error status code");
//                        Toast.makeText(newsBySourceActivity, "Error Status code", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    onGetNewsBySourceListener.onFailed(toString());
                }
            }

            @Override
            public void onFailure(Call<GetNewsBySourceDataBean> call, Throwable t) {
                onGetNewsBySourceListener.onFailed(t.toString());

            }
        });
    }
}
