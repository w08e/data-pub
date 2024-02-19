package com.w08e.data.pub.vo;

import java.util.List;

public class QueryResult<T> {

    public static <T> QueryResult<T> of(List<T> data) {
        return new QueryResult<>(data);
    }

    public static <T> QueryResult<T> of(List<T> data, Long total) {
        return new QueryResult<>(data, total);
    }

    private List<T> records;

    private Long total;

    public QueryResult(List<T> records, Long total) {
        this.total = total;
        this.records = records;
    }

    public QueryResult(List<T> records) {
        this.records = records;
    }

    public QueryResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
