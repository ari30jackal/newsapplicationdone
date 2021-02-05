package com.catha.mvp.ui.newsbysource;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.NewsBySource.NewsBySourceDataSource;
import com.catha.mvp.Data.remote.NewsBySource.NewsRepository;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryRepository;
import com.catha.mvp.ui.sourcebycategory.CategoryPresenter;
import com.catha.mvp.ui.sourcebycategory.CategoryView;

public class NewsBySourcePresenterImpl implements NewsBySourcePresenter {
    private NewsBySourceView newsBySourceView;
    private NewsRepository newsRepository;


    public NewsBySourcePresenterImpl(NewsBySourceView newsBySourceView, NewsRepository newsRepository) {
        this.newsBySourceView = newsBySourceView;
        this.newsRepository = newsRepository;
    }

    @Override
    public void doGetNewsBySource(String source) {
        newsRepository.getNewsBySource(source, new NewsBySourceDataSource.OnGetNewsBySourceListener() {
            @Override
            public void onSuccess(GetNewsBySourceDataBean getNewsBySourceDataBean) {
                newsBySourceView.onSuccessGetNewsBySource(getNewsBySourceDataBean);
            }

            @Override
            public void onFailed(String error) {
                newsBySourceView.onFailedGetNewsBySource(error);
            }
        });
    }


}
