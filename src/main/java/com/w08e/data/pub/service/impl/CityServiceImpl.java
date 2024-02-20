package com.w08e.data.pub.service.impl;

import ch.qos.logback.classic.spi.EventArgUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w08e.data.pub.dto.CityFilterDto;
import com.w08e.data.pub.entity.CityEntity;
import com.w08e.data.pub.es.CityEsRepository;
import com.w08e.data.pub.mapper.CityMapper;
import com.w08e.data.pub.service.CityService;
import com.w08e.data.pub.util.PageConvert;
import com.w08e.data.pub.vo.CityTreeVo;
import com.w08e.data.pub.vo.CityVo;
import com.w08e.data.pub.vo.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author W08E
 */
@Slf4j
@Service

public class CityServiceImpl extends ServiceImpl<CityMapper, CityEntity> implements CityService {

    @Resource
    private CityEsRepository cityEsRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public QueryResult<CityVo> list(CityFilterDto filter) {
        return baseMapper.searchWrapperForList(filter);
    }


    public QueryResult<CityVo> listEs(CityFilterDto filter) {

        CriteriaQuery query = new CriteriaQuery(new Criteria())
                .addCriteria(new Criteria("deep").lessThanEqual(filter.getMaxDeep()))
                .addCriteria(new Criteria("name").contains(filter.getKeywords()))
                .addCriteria(new Criteria("code").in(filter.getCode()))
                .addCriteria(new Criteria("parentCode").in(filter.getParentCode()));
        Page<CityEntity> cityEntities = elasticsearchTemplate.queryForPage(query, CityEntity.class);
        return PageConvert.toQueryResult(cityEntities.getContent(), cityEntities.getTotalElements(), this::transform);
    }

    @Override
    public List<CityTreeVo> tree(List<Integer> topCode, Integer maxDeep) {
        List<CityTreeVo> result = new ArrayList<>();

        QueryWrapper<CityEntity> queryWrapper = new QueryWrapper<>();
        if (maxDeep != null) {
            queryWrapper.lambda().le(CityEntity::getLevel, maxDeep);
        }

        List<CityEntity> cities = baseMapper.selectList(queryWrapper);


        HashMap<Integer, CityTreeVo> mapping = new HashMap<>();

        cities.stream().filter(item -> item.getLevel() == 1).forEach(item -> {
            if (topCode == null || topCode.isEmpty() || topCode.contains(item.getCode())) {
                CityTreeVo vo = transform(item);
                result.add(vo);
                mapping.put(vo.getCode(), vo);
            }
        });
        cities.stream().filter(item -> item.getLevel() == 2).forEach(item -> {

            CityTreeVo parentVo = mapping.get(item.getParentCode());
            CityTreeVo vo = transform(item);
            if (parentVo != null) {
                if (parentVo.getChildren() == null) {
                    parentVo.setChildren(new ArrayList<>());
                }
                parentVo.getChildren().add(vo);
                mapping.put(vo.getCode(), vo);
            }
            if (topCode != null && !topCode.isEmpty() && topCode.contains(item.getCode())) {
                result.add(vo);
                if (!mapping.containsKey(vo.getCode())) mapping.put(vo.getCode(), vo);
            }

        });
        cities.stream().filter(item -> item.getLevel() == 3).forEach(item -> {
            CityTreeVo parentVo = mapping.get(item.getParentCode());
            CityTreeVo vo = transform(item);
            if (parentVo != null) {
                if (parentVo.getChildren() == null) {
                    parentVo.setChildren(new ArrayList<>());
                }
                parentVo.getChildren().add(vo);
                mapping.put(vo.getCode(), vo);
            }
            if (topCode != null && !topCode.isEmpty() && topCode.contains(item.getCode())) {
                result.add(vo);
                if (!mapping.containsKey(vo.getCode())) mapping.put(vo.getCode(), vo);
            }
        });
        return result;
    }

    @Override
    public List<CityTreeVo> treeEs(List<Integer> topCode, Integer maxDeep) {
        List<CityTreeVo> result = new ArrayList<>();


        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (maxDeep != null) {
            builder.withQuery(QueryBuilders.rangeQuery("deep").lte(maxDeep));
        }

        Page<CityEntity> cities = cityEsRepository.search(builder.build());

        HashMap<Integer, CityTreeVo> mapping = new HashMap<>();

        cities.stream().filter(item -> item.getLevel() == 1).forEach(item -> {
            if (topCode == null || topCode.isEmpty() || topCode.contains(item.getCode())) {
                CityTreeVo vo = transform(item);
                result.add(vo);
                mapping.put(vo.getCode(), vo);
            }
        });
        cities.stream().filter(item -> item.getLevel() == 2).forEach(item -> {

            CityTreeVo parentVo = mapping.get(item.getParentCode());
            CityTreeVo vo = transform(item);
            if (parentVo != null) {
                if (parentVo.getChildren() == null) {
                    parentVo.setChildren(new ArrayList<>());
                }
                parentVo.getChildren().add(vo);
                mapping.put(vo.getCode(), vo);
            }
            if (topCode != null && !topCode.isEmpty() && topCode.contains(item.getCode())) {
                result.add(vo);
                if (!mapping.containsKey(vo.getCode())) mapping.put(vo.getCode(), vo);
            }

        });
        cities.stream().filter(item -> item.getLevel() == 3).forEach(item -> {
            CityTreeVo parentVo = mapping.get(item.getParentCode());
            CityTreeVo vo = transform(item);
            if (parentVo != null) {
                if (parentVo.getChildren() == null) {
                    parentVo.setChildren(new ArrayList<>());
                }
                parentVo.getChildren().add(vo);
                mapping.put(vo.getCode(), vo);
            }
            if (topCode != null && !topCode.isEmpty() && topCode.contains(item.getCode())) {
                result.add(vo);
                if (!mapping.containsKey(vo.getCode())) mapping.put(vo.getCode(), vo);
            }
        });
        return result;
    }

    public void syncCity2Es() {
        QueryWrapper<CityEntity> queryWrapper = new QueryWrapper();
        List<CityEntity> cities = baseMapper.selectList(queryWrapper);
        cityEsRepository.saveAll(cities);
    }

    private CityTreeVo transform(CityEntity entity) {
        CityTreeVo vo = new CityTreeVo();
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
