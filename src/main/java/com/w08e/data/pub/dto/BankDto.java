package com.w08e.data.pub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jinyuewang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BankDto extends QueryParameter{

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
   private String name;

   private String value;
}
