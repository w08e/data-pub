package com.w08e.data.pub.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w08e.data.pub.dto.QueryParameter;
import com.w08e.data.pub.vo.QueryResult;

import java.util.List;
import java.util.function.Function;

public class PageConvert {

    public static Page toPage(QueryParameter queryParameter) {
        Page page = new Page();
        page.setCurrent(queryParameter.getCurrent());
        page.setSize(queryParameter.getSize());
        page.setSearchCount(queryParameter.isWithTotal());
        return page;
    }

    public static <F, T> QueryResult<T> toQueryResult(Page<F> from, Function<? super F, ? extends T> func) {
        return new QueryResult(StreamUtil.map(from.getRecords(), func), from.getTotal());
    }

    public static <F, T> QueryResult<T> toQueryResult(List<F> records, Long total, Function<? super F, ? extends T> func) {
        return new QueryResult(StreamUtil.map(records, func), total);
    }
}
