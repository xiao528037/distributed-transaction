package com.xiao.cloud.messageaccount.message;

import com.alibaba.fastjson.JSON;
import com.xiao.cloud.cloudcommon.message.account.dto.AccountDto;
import com.xiao.cloud.messageaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:57:54
 * @description
 */
@Component
@Slf4j(topic = "RocketMQConsumer")
public class RocketMQConsumer {

    private final AccountService accountService;

    public RocketMQConsumer(AccountService accountService) {
        this.accountService = accountService;
    }


    @Component
    @RocketMQMessageListener(topic = "recharge_topic", consumerGroup = "account_group")
    public class RechargeConsumer implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt message) {
            AccountDto accountDto = JSON.parseObject(message.getBody(), AccountDto.class, null);
            accountService.deductionBalance(accountDto);
        }
    }
}
