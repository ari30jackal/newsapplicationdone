package com.catha.mvp.Data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetNewsBySourceDataBean {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<NewsBySourceDataBean> articles;

    @SerializedName("status")
    private String status;

    public int getTotalResults() {
        return totalResults;
    }

    public List<NewsBySourceDataBean> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }
}