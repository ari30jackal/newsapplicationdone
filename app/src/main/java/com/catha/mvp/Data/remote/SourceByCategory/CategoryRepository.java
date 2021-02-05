package com.catha.mvp.Data.remote.SourceByCategory;

import androidx.annotation.NonNull;

import com.catha.mvp.Data.model.GetSourcesDataBean;

public class CategoryRepository implements CategoryDataSource {
    private final CategoryDataSource categoryDataSource;
    private static CategoryRepository instance = null;

    public CategoryRepository(@NonNull CategoryDataSource categoryDataSource) {


        this.categoryDataSource = categoryDataSource;
    }

    public static CategoryRepository getInstance(CategoryDataSource remoteDataSource) {
        if (instance == null) {

            instance = new CategoryRepository(remoteDataSource);

        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    @Override
    public void getSourceByCategory(String category, OnGetSourcesByCategoryListener onGetSourcesByCategoryListener) {
        categoryDataSource.getSourceByCategory(category, new OnGetSourcesByCategoryListener() {
            @Override
            public void onSuccess(GetSourcesDataBean getSourcesDataBean) {
                onGetSourcesByCategoryListener.onSuccess(getSourcesDataBean);
            }

            @Override
            public void onFailed(String error) {
                onGetSourcesByCategoryListener.onFailed(error);
            }
        });
    }
}
