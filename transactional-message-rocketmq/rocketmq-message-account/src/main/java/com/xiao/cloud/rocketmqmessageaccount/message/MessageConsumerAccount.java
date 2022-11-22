package com.xiao.cloud.rocketmqmessageaccount.message;

import com.alibaba.fastjson.JSON;
import com.xiao.cloud.cloudcommon.message_tx.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.message_tx.account.entity.MessageTxAccount;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import com.xiao.cloud.rocketmqmessageaccount.service.AccountServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
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
public class MessageConsumerAccount {

    private final AccountServer accountServer;

    public MessageConsumerAccount(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Service
    @RocketMQMessageListener(topic = "message_tx_account", consumerGroup = "tx_account_consumer")
    public class ConsumerStock implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt message) {
            MessageTxOrder messageTxOrder = JSON.parseObject(message.getBody(), MessageTxOrder.class);
            String transactionId = message.getTransactionId();
            String productId = message.getProperty("productId");
            AccountDTO accountDTO = buildAccountDTO(messageTxOrder);
            accountServer.deductionAmount(accountDTO, productId, transactionId);
            log.info("消费者是 >>> {} 收到的消息是 >>> {} tag >>> {}", "One", new String(message.getBody()), message.getTags());
        }
    }

    private AccountDTO buildAccountDTO(MessageTxOrder order) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }
}
