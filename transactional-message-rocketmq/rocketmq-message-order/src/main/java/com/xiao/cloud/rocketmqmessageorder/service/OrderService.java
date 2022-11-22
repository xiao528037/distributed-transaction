package com.xiao.cloud.rocketmqmessageorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-21 17:32:13
 * @description
 */
public interface OrderService extends IService<MessageTxOrder> {

    /**
     * 创建订单
     *
     * @param messageTxOrder
     *         订单信息
     * @return 订单信息
     */
    MessageTxOrder createOrder(MessageTxOrder messageTxOrder);

    /**
     * 扣减库存和金额
     *
     * @param messageTxOrder
     * @param TransactionalId
     */
    void deductionStockAmount(MessageTxOrder messageTxOrder, String TransactionalId);
}
