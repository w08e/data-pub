package com.w08e.data.pub.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jinyuewang
 */
@Data
public class CityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private int code;

    /**
     * 父节点编码
     */
    private int parentCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 有多少个直接下级
     */
    private int childrenNum;

    /**
     * code路径， 例如: 110000|110100|110101
     */
    private String path;


    /**
     * 名称路径， 例如: 北京市|北京市|东城区
     */
    private String pathName;

    /**
     * 数据深度, 1代表第一级
     */
    private Integer deep;
}
