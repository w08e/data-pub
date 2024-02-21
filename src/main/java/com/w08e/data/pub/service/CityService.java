package com.w08e.data.pub.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.w08e.data.pub.dto.CityFilterDto;
import com.w08e.data.pub.entity.CityEntity;
import com.w08e.data.pub.vo.CityTreeVo;
import com.w08e.data.pub.vo.CityVo;
import com.w08e.data.pub.vo.QueryResult;

import java.util.List;

/**
 * @author jinyuewang
 */
public interface CityService extends IService<CityEntity> {
    QueryResult<CityVo> list(CityFilterDto filter);

    public List<CityTreeVo> tree(List<Integer> topCode, Integer maxDeep);

    public List<CityTreeVo> treeEs(List<Integer> topCode, Integer maxDeep);

    void deleteEs();
}
