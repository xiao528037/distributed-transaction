package com.xiao.cloud.rocketmqmessageorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.message_tx.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.message_tx.account.dto.AccountNestedDTO;
import com.xiao.cloud.cloudcommon.message_tx.inventory.dto.InventoryDTO;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import com.xiao.cloud.cloudcommon.message_tx.order.mapper.OrderMapper;
import com.xiao.cloud.rocketmqmessageorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-21 17:32:39
 * @description
 */
@Service("orderService")
@Slf4j(topic = "OrderServiceImpl")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, MessageTxOrder> implements OrderService {


    private final RocketMQTemplate rocketMQTemplate;

    private final OrderMapper orderMapper;

    public OrderServiceImpl(RocketMQTemplate rocketMQTemplate,
                            OrderMapper orderMapper) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.orderMapper = orderMapper;
    }

    @Override
    public MessageTxOrder createOrder(MessageTxOrder messageTxOrder) {
        Message<MessageTxOrder> message = MessageBuilder
                .withPayload(messageTxOrder)
                .setHeader(MessageHeaders.CONTENT_TYPE, "application/json;charset=utf8")
                .setHeader("productId", messageTxOrder.getProductId())
                .build();
        TransactionSendResult message_tx_account = rocketMQTemplate.sendMessageInTransaction("message_tx_account", message, null);
        if (message_tx_account.getLocalTransactionState().equals(LocalTransactionState.ROLLBACK_MESSAGE) || message_tx_account.getLocalTransactionState().equals(LocalTransactionState.UNKNOW)) {
            return null;
        }
        rocketMQTemplate.sendMessageInTransaction("message_tx_stock", message, null);
        return messageTxOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductionStockAmount(MessageTxOrder messageTxOrder, String transactionalId) {
        if (orderMapper.isExist(transactionalId) > 0) {
            throw new RuntimeException("请勿重复提交");
        }
        int save = orderMapper.save(messageTxOrder);
        int saveTransactional = orderMapper.saveTransactional(messageTxOrder.getNumber(), transactionalId);
        if (save <= 0 || saveTransactional <= 0) {
            throw new RuntimeException("处理失败");
        }
    }

    private AccountDTO buildAccountDTO(MessageTxOrder order) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }

    private InventoryDTO buildInventoryDTO(HmilyTccOrder order) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        return inventoryDTO;
    }

    private AccountNestedDTO buildAccountNestedDTO(HmilyTccOrder order) {
        AccountNestedDTO nestedDTO = new AccountNestedDTO();
        nestedDTO.setAmount(order.getTotalAmount());
        nestedDTO.setUserId(order.getUserId());
        nestedDTO.setProductId(order.getProductId());
        nestedDTO.setCount(order.getCount());
        return nestedDTO;
    }

}
