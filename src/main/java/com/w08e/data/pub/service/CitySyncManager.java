package com.w08e.data.pub.service;

import cn.hutool.core.bean.BeanUtil;
import com.w08e.data.pub.dto.CityDto;
import com.w08e.data.pub.entity.CityEntity;
import com.w08e.data.pub.es.CityEsRepository;
import com.w08e.data.pub.mapper.CityMapper;
import com.w08e.data.pub.mq.publish.CityPublishEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * es与数据库同步策略
 *
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/2/20 09:36
 */

@Log4j2
@Service
public class CitySyncManager {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private CityEsRepository cityEsRepository;

    @Resource
    private CityPublishEvent cityPublishEvent;


    /**
     * 同步双写城市数据
     * <p>
     * 及时性高，适用于数据量小的场景，实时性高 代码量多
     * 性能低，耦合度高，存在数据双丢的风险
     */
    public void syncWrite(CityDto cityDto) {
        //省略校验逻辑....
        //数据库写入
        CityEntity cityEntity = new CityEntity();
        BeanUtil.copyProperties(cityDto, cityEntity);
        cityMapper.insert(cityEntity);

        //es写入
        cityEsRepository.save(cityEntity);
    }

    /**
     * 异步双写mq 城市数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void asyncWrite(CityDto cityDto) {
        //省略校验逻辑....
        //数据库写入
        CityEntity cityEntity = new CityEntity();
        BeanUtil.copyProperties(cityDto, cityEntity);
        cityMapper.insert(cityEntity);

        //mq发布城市创建事件
        cityPublishEvent.publishCityCreatedEvent(cityEntity);
    }

    /**
     * 异步双写定时任务 城市数据
     * 实时性不高情况适用
     * 不影响原业务逻辑
     */
    public void asyncWriteSchedule(CityDto cityDto) {
        //定时扫表，用update_time字段判断是否需要同步
        //增量数据导入es

        //省略定时任务写入es逻辑....
    }
}
