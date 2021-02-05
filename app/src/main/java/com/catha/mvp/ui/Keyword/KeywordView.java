package com.catha.mvp.ui.Keyword;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.base.BaseView;
import com.catha.mvp.ui.sourcebycategory.CategoryPresenter;

public interface KeywordView extends BaseView<KeywordPresenter> {


    void onSuccessByKeyword(GetKeywordDataBean getKeywordDataBean);

    void onFailedGetByKeyword(String error);
}
