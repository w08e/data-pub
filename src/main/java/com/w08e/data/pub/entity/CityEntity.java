package com.w08e.data.pub.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.w08e.data.pub.vo.CityTreeVo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;


/**
 * @author jinyuewang
 */
@Data
@TableName("new_area")
@Document(indexName = "pub-data-city")
public class CityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@TableField(value = "code")
	private Integer code;

	@TableField(value = "parent_code")
	@Field(type=FieldType.Integer)
	private Integer parentCode;

	@Field(type= FieldType.Keyword)
	private String name;

	@TableField(value = "child_num")
	@Field(type=FieldType.Integer)
	private Integer childrenNum;

	@TableField(value = "path_code")
	@Field(type=FieldType.Keyword)
	private String pathCode;

	@TableField(value = "path_name")
	@Field(type=FieldType.Keyword)
	private String pathName;

	@Field(type=FieldType.Integer)
	private Integer level;

	public CityTreeVo toTreeVo() {
		CityTreeVo vo = new CityTreeVo();
		vo.setCode(getCode());
		vo.setParentCode(getParentCode());
		vo.setName(getName());
		vo.setChildrenNum(getChildrenNum());
		vo.setPath(getPathCode());
		vo.setPathName(getPathName());
		vo.setDeep(getLevel());
		return vo;
	}


}
