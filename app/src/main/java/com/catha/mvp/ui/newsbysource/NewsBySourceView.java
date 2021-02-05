package com.catha.mvp.ui.newsbysource;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.ui.sourcebycategory.CategoryPresenter;

public interface NewsBySourceView {
    void onSuccessGetNewsBySource(GetNewsBySourceDataBean getNewsBySourceDataBean);
    void onFailedGetNewsBySource(String error);

    void onFailedNewsBySource(String error);

    void setPresenter(CategoryPresenter presenter);
}

