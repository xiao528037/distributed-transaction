package com.xiao.cloud.rocketmqmessagestock.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-21 17:24:54
 * @description
 */

@Slf4j
@Component
public class MessageConsumerStock {

    @Service
    @RocketMQMessageListener(topic = "message_tx_stock", consumerGroup = "consumer_one_group")
    public class ConsumerStock implements RocketMQListener<MessageExt> {

        @Override
        public void onMessage(MessageExt message) {
            log.info("消费者是 >>> {} 收到的消息是 >>> {} tag >>> {}", "One", new String(message.getBody()), message.getTags());
        }
    }
}
