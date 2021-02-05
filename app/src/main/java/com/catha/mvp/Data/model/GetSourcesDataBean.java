package com.catha.mvp.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSourcesDataBean {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("sources")
    @Expose
    public List<SourceDataBean> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceDataBean> getSources() {
        return sources;
    }

    public void setSources(List<SourceDataBean> sources) {
        this.sources = sources;
    }
}
