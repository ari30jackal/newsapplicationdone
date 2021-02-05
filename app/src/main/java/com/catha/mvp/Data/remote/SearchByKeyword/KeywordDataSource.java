package com.catha.mvp.Data.remote.SearchByKeyword;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;

public interface KeywordDataSource {
    void getByKeyword(String keyword, KeywordDataSource.OnGetByKeywordListener onGetByKeywordListener);

    interface OnGetByKeywordListener {

        void onSuccess(GetKeywordDataBean getKeywordDataBean);

        void onFailed(String error);
    }
}
