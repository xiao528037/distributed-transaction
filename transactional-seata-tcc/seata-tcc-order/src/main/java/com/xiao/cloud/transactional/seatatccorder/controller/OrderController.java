package com.xiao.cloud.transactional.seatatccorder.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccOrder;
import com.xiao.cloud.transactional.seatatccorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:16:37
 * @description
 */
@RestController
@RequestMapping("/tcc/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("addOrder")
    public CommonResult<TccOrder> addOrder(TccOrder tccOrder) {
        orderService.save(tccOrder);
        return new CommonResult<>(0x00001L, "操作成功", tccOrder);
    }
}
