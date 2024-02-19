package com.w08e.data.pub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author jinyuewang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CityFilterDto extends QueryParameter {

    /**
     * 编码
     */
    private List<Integer> code;

    /**
     * 父编码
     */
    private List<Integer> parentCode;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 最大深度, 1代表第一级
     */
    private Integer maxDeep;
}
