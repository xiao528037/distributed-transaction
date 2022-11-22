package com.xiao.cloud.rocketmqmessageorder.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.enums.OrderStatusEnum;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import com.xiao.cloud.rocketmqmessageorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-21 17:59:58
 * @description
 */
@RestController
@RequestMapping("/rocketmq/order")
@Slf4j(topic = "OrderController")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public CommonResult<MessageTxOrder> createOrder(Integer count, BigDecimal amount) {
        MessageTxOrder messageTxOrder = buildOrder(count, amount);
        MessageTxOrder order = orderService.createOrder(messageTxOrder);
        if (order == null) {
            return new CommonResult<>(0x000002L, "发送失败", null);
        }
        return new CommonResult<>(0x000001L, "发送成功", order);
    }

    private MessageTxOrder buildOrder(Integer count, BigDecimal amount) {
        MessageTxOrder order = new MessageTxOrder();
        order.setCreateTime(new Date());
        order.setNumber(String.valueOf(UUID.randomUUID()));
        //demo中的表里只有商品id为 1的数据
        order.setProductId("1");
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中 表里面存的用户id为1
        order.setUserId("1");
        return order;
    }
}
