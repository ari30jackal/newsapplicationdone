package com.catha.mvp.Data.remote.NewsBySource;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;

public class NewsRepository implements NewsBySourceDataSource {
    private final NewsBySourceDataSource newsBySourceDataSource;
    private static NewsRepository instance = null;

    public NewsRepository(@NonNull NewsBySourceDataSource newsBySourceDataSource) {


        this.newsBySourceDataSource = newsBySourceDataSource;
    }

    public static NewsRepository getInstance(NewsBySourceDataSource remoteDataSource) {
        if (instance == null) {

            instance = new NewsRepository(remoteDataSource);

        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    @Override
    public void getNewsBySource(String source, OnGetNewsBySourceListener onGetNewsBySourceListener) {
        newsBySourceDataSource.getNewsBySource(source, new OnGetNewsBySourceListener() {
            @Override
            public void onSuccess(GetNewsBySourceDataBean getNewsBySourceDataBean) {
                onGetNewsBySourceListener.onSuccess(getNewsBySourceDataBean);
            }

            @Override
            public void onFailed(String error) {
                onGetNewsBySourceListener.onFailed(error);

            }
        });
    }
}
