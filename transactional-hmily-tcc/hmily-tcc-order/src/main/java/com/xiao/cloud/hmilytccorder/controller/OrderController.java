package com.xiao.cloud.hmilytccorder.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.hmilytccorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:48:01
 * @description
 */
@RestController
@RequestMapping("/hmily/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @PostMapping("/orderPay")
    public CommonResult<HmilyTccOrder> orderPay(Integer count, BigDecimal amount) {
        HmilyTccOrder order = orderService.orderPay(count, amount);
        return new CommonResult<>(0x00001L, "订单生成成功", order);
    }

    @GetMapping("/getOrder/{orderId}")
    public CommonResult<HmilyTccOrder> getOrder(@PathVariable("orderId") Long orderId) {
        HmilyTccOrder tccOrder = orderService.getById(orderId);
        return new CommonResult<>(0x00001L, "查询订单信息", tccOrder);
    }

    @GetMapping("/getAccount/{userId}")
    public CommonResult<HmilyTccAccount> getAccountById(@PathVariable("userId") String userId) {
        HmilyTccAccount account = orderService.getAccountById(userId);
        return new CommonResult<>(0x00001L, "查询用户信息", account);
    }

    @GetMapping("/getInventory/{productId}")
    public CommonResult<HmilyTccInventory> getInventoryById(@PathVariable("productId") String productId) {
        HmilyTccInventory inventory = orderService.getInventoryById(productId);
        return new CommonResult<>(0x00001L, "查询库存信息", inventory);
    }
}
