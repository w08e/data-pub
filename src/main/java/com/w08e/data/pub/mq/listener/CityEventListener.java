package com.w08e.data.pub.mq.listener;

import com.alibaba.fastjson.JSON;
import com.w08e.data.pub.entity.CityEntity;
import com.w08e.data.pub.es.CityEsRepository;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/2/20 12:08
 */
@Log4j2
@Component
@RocketMQMessageListener(topic = "city-event-topic", consumerGroup = "w08e_data_hub_city_group")
public class CityEventListener implements RocketMQListener<String> {

    @Setter(onMethod_ = @Autowired)
    private CityEsRepository cityEsRepository;

    @Override
    public void onMessage(String message) {
        try {
            CityEntity city = JSON.parseObject(message, CityEntity.class);
            log.info("接收到城市创建事件: {}", city);
            cityEsRepository.save(city);
        } catch (Exception e) {
            log.info("城市创建事件消费失败", e);
            throw new RuntimeException(e);
        }
    }
}