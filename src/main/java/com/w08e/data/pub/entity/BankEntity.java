package com.w08e.data.pub.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.w08e.data.pub.vo.BankVo;
import lombok.Data;

import java.io.Serializable;


/**
 * @author jinyuewang
 */
@Data
@TableName("t_bank")

public class BankEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编码
	 */
	@TableField(value = "code")
	private String code;

	private String name;

	private String value;


	public BankVo toVo() {
		BankVo vo = new BankVo();
		vo.setCode(getCode());
		vo.setName(getName());
		vo.setValue(getValue());
		return vo;
	}

}
