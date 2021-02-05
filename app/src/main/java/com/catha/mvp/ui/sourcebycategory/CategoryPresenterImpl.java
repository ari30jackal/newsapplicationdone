package com.catha.mvp.ui.sourcebycategory;

import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryRepository;

public class CategoryPresenterImpl implements CategoryPresenter {
    private CategoryView categoryView;
    private CategoryRepository categoryRepository;


    public CategoryPresenterImpl(CategoryView categoryView, CategoryRepository categoryRepository) {
        this.categoryView = categoryView;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void doGetSourcesByCategory(String category) {
        categoryRepository.getSourceByCategory(category, new CategoryDataSource.OnGetSourcesByCategoryListener() {
            @Override
            public void onSuccess(GetSourcesDataBean getSourcesDataBean) {
                categoryView.onSuccessGetSourcesByCategory(getSourcesDataBean);
            }

            @Override
            public void onFailed(String error) {
                categoryView.onFailedGetSourcesByCategory(error);
            }
        });
    }

    @Override
    public void start() {

    }
}
