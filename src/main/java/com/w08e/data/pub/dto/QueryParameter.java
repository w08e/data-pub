package com.w08e.data.pub.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  查询参数
 * @author W08E
 */
@Setter
@Getter
public class QueryParameter {

    /**
     * 页码
     */
    private Long current=1L;

    /**
     * 每页条数
     */
    private Long size=10L;

    /**
     * 是否查询总条目
     */
    private boolean withTotal;

    /**
     * 排序
     */
    private List<Sort> sorts;


    @Setter
    @Getter
    public static class Sort {

        private String field;

        private Direction direction;

    }


    public enum Direction {
        ASCENDING,
        DESCENDING
    }
}
