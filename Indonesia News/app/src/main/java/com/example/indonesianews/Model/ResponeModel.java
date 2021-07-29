package com.example.indonesianews.Model;

import java.util.List;

public class ResponeModel {
    private String status;
    private int totalResult;
    private List<BeritaModel> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<BeritaModel> getArticles() {
        return articles;
    }

    public void setArticles(List<BeritaModel> articles) {
        this.articles = articles;
    }
}
