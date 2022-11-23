package com.xiao.cloud.rocketmqmessageorder.message;


import com.alibaba.fastjson.JSON;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import com.xiao.cloud.cloudcommon.message_tx.order.mapper.OrderMapper;
import com.xiao.cloud.rocketmqmessageorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.messaging.Message;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-21 17:45:30
 * @description 消息发送成功执行扣除等业务逻辑
 */

@Slf4j(topic = "TransactionExecutorStockImpl")
@RocketMQTransactionListener(corePoolSize = 2, maximumPoolSize = 10)
public class TransactionExecutorStockImpl implements RocketMQLocalTransactionListener {

    private final OrderMapper orderMapper;

    private final OrderService orderService;

    public TransactionExecutorStockImpl(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String rocketmq_transaction_id = (String) msg.getHeaders().get("rocketmq_TRANSACTION_ID");
        byte[] payload = (byte[]) msg.getPayload();
        MessageTxOrder messageTxOrder = JSON.parseObject(payload, MessageTxOrder.class);
        try {
            orderService.deductionStockAmount(messageTxOrder, rocketmq_transaction_id);
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        log.info("消息主体 >>> {} 消息事务ID >>> {} ", messageTxOrder, rocketmq_transaction_id);
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String rocketmq_transaction_id = (String) msg.getHeaders().get("rocketmq_TRANSACTION_ID");
        //查询不到事务信息
        if (orderMapper.isExist(rocketmq_transaction_id) <= 0) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
