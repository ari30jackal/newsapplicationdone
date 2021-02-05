package com.catha.mvp.ui.sourcebycategory;

import com.catha.mvp.base.BasePresenter;

public interface CategoryPresenter extends BasePresenter {
    void doGetSourcesByCategory(String category);
}
