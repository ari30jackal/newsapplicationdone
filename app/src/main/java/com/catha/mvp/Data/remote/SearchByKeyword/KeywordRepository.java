package com.catha.mvp.Data.remote.SearchByKeyword;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryRepository;

import androidx.annotation.NonNull;

public class KeywordRepository implements KeywordDataSource {

    private final KeywordDataSource keywordDataSource;
    private static KeywordRepository instance = null;

    public KeywordRepository(@NonNull KeywordDataSource keywordDataSource) {


        this.keywordDataSource = keywordDataSource;
    }

    public static KeywordRepository getInstance(KeywordDataSource remoteDataSource) {
        if (instance == null) {

            instance = new KeywordRepository(remoteDataSource);

        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    @Override
    public void getByKeyword(String keyword, KeywordDataSource.OnGetByKeywordListener onGetByKeywordListener) {
        keywordDataSource.getByKeyword(keyword, new KeywordDataSource.OnGetByKeywordListener() {
            @Override
            public void onSuccess(GetKeywordDataBean getKeywordDataBean) {
                onGetByKeywordListener.onSuccess(getKeywordDataBean);
            }

            @Override
            public void onFailed(String error) {
                onGetByKeywordListener.onFailed(error);
            }
        });
    }



}
