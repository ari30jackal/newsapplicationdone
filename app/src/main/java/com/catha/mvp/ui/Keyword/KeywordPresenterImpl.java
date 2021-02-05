package com.catha.mvp.ui.Keyword;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.SearchByKeyword.KeywordDataSource;
import com.catha.mvp.Data.remote.SearchByKeyword.KeywordRepository;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryRepository;
import com.catha.mvp.ui.sourcebycategory.CategoryView;

public class KeywordPresenterImpl implements KeywordPresenter {
    private KeywordView keywordView;
    private KeywordRepository keywordRepository;


    public KeywordPresenterImpl(KeywordView keywordView, KeywordRepository keywordRepository) {
        this.keywordView = keywordView;
        this.keywordRepository = keywordRepository;
    }

    @Override
    public void doGetByKeyword(String keyword) {
        keywordRepository.getByKeyword(keyword, new KeywordDataSource.OnGetByKeywordListener() {
            @Override
            public void onSuccess(GetKeywordDataBean getKeywordDataBean) {
                keywordView.onSuccessByKeyword(getKeywordDataBean);
            }

            @Override
            public void onFailed(String error) {
                keywordView.onFailedGetByKeyword(error);
            }
        });
    }

    @Override
    public void start() {

    }
}
