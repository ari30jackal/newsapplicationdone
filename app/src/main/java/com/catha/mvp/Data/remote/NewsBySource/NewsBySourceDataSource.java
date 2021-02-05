package com.catha.mvp.Data.remote.NewsBySource;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;


public interface NewsBySourceDataSource {
    void getNewsBySource(String source, OnGetNewsBySourceListener onGetNewsBySourceListener);

    interface OnGetNewsBySourceListener {

        void onSuccess(GetNewsBySourceDataBean getNewsBySourceDataBean);

        void onFailed(String error);
    }
}
