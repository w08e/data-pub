package com.w08e.data.pub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jinyuewang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BankCnapsDto extends QueryParameter {

    /**
     * 城市code
     */
    private Integer cityCode;

    /**
     * 开户行code
     */
    private String bankCode;
    /**
     * 关键字
     */
    private String keyword;
}
