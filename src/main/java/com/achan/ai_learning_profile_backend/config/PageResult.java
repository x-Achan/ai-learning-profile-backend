package com.achan.ai_learning_profile_backend.common;

import java.util.List;

public class PageResult<T> {

    private Long page;
    private Long size;
    private Long total;
    private Long pages;
    private List<T> records;

    public PageResult() {
    }

    public PageResult(Long page, Long size, Long total, Long pages, List<T> records) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.pages = pages;
        this.records = records;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}