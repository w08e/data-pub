package com.w08e.data.pub.dto;

import java.util.List;

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

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public boolean isWithTotal() {
        return withTotal;
    }

    public void setWithTotal(boolean withTotal) {
        this.withTotal = withTotal;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }


    public static class Sort {

        private String field;

        private Direction direction;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }


    public enum Direction {
        ASCENDING,
        DESCENDING
    }
}
