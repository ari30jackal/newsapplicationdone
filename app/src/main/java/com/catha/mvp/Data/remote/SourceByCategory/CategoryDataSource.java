package com.catha.mvp.Data.remote.SourceByCategory;

import com.catha.mvp.Data.model.GetSourcesDataBean;

public interface CategoryDataSource {
    void getSourceByCategory(String category, OnGetSourcesByCategoryListener onGetSourcesByCategoryListener);

    interface OnGetSourcesByCategoryListener {

        void onSuccess(GetSourcesDataBean getSourcesDataBean);

        void onFailed(String error);
    }
}
