package com.w08e.data.pub.dto;

import lombok.Data;


/**
 * @author jinyuewang
 */
@Data
public class CityDto {


	private Integer code;

	private Integer parentCode;

	private String name;

	private Integer childrenNum;

	private String pathCode;

	private String pathName;

	private Integer level;

}
