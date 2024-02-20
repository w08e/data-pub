package com.w08e.data.pub.mapper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w08e.data.pub.dto.CityFilterDto;
import com.w08e.data.pub.entity.CityEntity;
import com.w08e.data.pub.util.PageConvert;
import com.w08e.data.pub.vo.CityVo;
import com.w08e.data.pub.vo.QueryResult;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author W08E
 */
@Mapper
public interface CityMapper extends BaseMapper<CityEntity> {
    default QueryResult searchWrapperForList(CityFilterDto filter) {
        Page page = PageConvert.toPage(filter);
        Page result = this.selectPage(page,
                Wrappers.lambdaQuery(CityEntity.class)
                        .in(CollectionUtil.isNotEmpty(filter.getCode()), CityEntity::getCode, filter.getCode())
                        .in(CollectionUtil.isNotEmpty(filter.getParentCode()), CityEntity::getCode, filter.getParentCode())
                        .le(ObjectUtil.isNotEmpty(filter.getMaxDeep()), CityEntity::getLevel, filter.getMaxDeep())
                        .like(StringUtils.isNotEmpty(filter.getKeywords()), CityEntity::getName, filter.getKeywords())
        );
        return PageConvert.toQueryResult(result,this::toVo);
    }

    private CityVo toVo(CityEntity entity) {
        CityVo vo = new CityVo();
        vo.setCode(entity.getCode());
        vo.setParentCode(entity.getParentCode());
        vo.setName(entity.getName());
        vo.setChildrenNum(entity.getChildrenNum());
        vo.setPath(entity.getPathCode());
        vo.setPathName(entity.getPathName());
        vo.setDeep(entity.getLevel());
        return vo;
    }
}