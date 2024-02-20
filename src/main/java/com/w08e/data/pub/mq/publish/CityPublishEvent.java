package com.w08e.data.pub.mq.publish;

import com.alibaba.fastjson.JSON;
import com.w08e.data.pub.entity.CityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/2/20 12:06
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class CityPublishEvent {

    private final RocketMQTemplate rocketMqTemplate;

    /**
     * 发布城市创建事件
     */
    public void publishCityCreatedEvent(CityEntity city) {
        // 将城市实体转换为消息体（例如JSON字符串）
        String messagePayload = JSON.toJSONString(city);

        // 添加事件类型标识
        Message<String> message = MessageBuilder.withPayload(messagePayload)
                .setHeader("eventType", "city.created")
                .build();

        rocketMqTemplate.asyncSend("city-event-topic", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送城市创建事件成功"+ sendResult.getMsgId() );
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("发送城市创建事件失败", throwable);
            }
        });
    }
}
