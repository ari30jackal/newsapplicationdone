package com.catha.mvp.ui.sourcebycategory;

import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.base.BaseView;

public interface CategoryView extends BaseView<CategoryPresenter> {
    void onSuccessGetSourcesByCategory(GetSourcesDataBean getSourcesDataBean);

    void onFailedGetSourcesByCategory(String error);

}
