package com.w08e.data.pub.vo;

import lombok.Data;

/**
 * @author jinyuewang
 */
@Data
public class BankCardVo {

    /**
     * 超级网银号
     */
    private String bankCardEicon;

    /**
     * 银行名称
     */
    private String name;

    /**
     * 银行简称
     */
    private String nameShort;


    /**
     * code
     */
    private String code;

    /**
     * 清算行号
     */
    private String bankCardClear;
}
