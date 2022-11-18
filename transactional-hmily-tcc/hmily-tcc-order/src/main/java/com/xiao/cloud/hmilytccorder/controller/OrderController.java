package com.xiao.cloud.hmilytccorder.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.entity.HmilyTccOrder;
import com.xiao.cloud.hmilytccorder.openapi.AccountApi;
import com.xiao.cloud.hmilytccorder.openapi.InventoryApi;
import com.xiao.cloud.hmilytccorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

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

    private final AccountApi accountApi;

    private final InventoryApi inventoryApi;

    public OrderController(OrderService orderService, AccountApi accountApi, InventoryApi inventoryApi) {
        this.orderService = orderService;
        this.accountApi = accountApi;
        this.inventoryApi = inventoryApi;
    }

    @PostMapping("/createOrder")
    public CommonResult<HmilyTccOrder> addOrder(HmilyTccOrder hmilyTccOrder) {
        HmilyTccOrder order = orderService.addOrder(hmilyTccOrder);
        return new CommonResult<>(0x00001L, "订单生成成功", order);
    }

    @GetMapping("/getOrder")
    public CommonResult<HmilyTccOrder> getOrder(Long orderId) {
        HmilyTccOrder tccOrder = orderService.getById(orderId);
        return new CommonResult<>(0x00001L, "订单生成成功", tccOrder);
    }

    @GetMapping("/getAccount/{userId}")
    public CommonResult<HmilyTccAccount> getAccountById(@PathVariable("userId") String userId) {
        CommonResult<HmilyTccAccount> result = accountApi.getAccount(userId);
        HmilyTccAccount account = result.getData();
        return new CommonResult<>(0x00001L, "订单生成成功", account);
    }

    @GetMapping("/getInventory/{productId}")
    public CommonResult<HmilyTccInventory> getInventoryById(@PathVariable("productId") String productId) {
        CommonResult<HmilyTccInventory> result = inventoryApi.getInventoryById(productId);
        HmilyTccInventory inventory = result.getData();
        return new CommonResult<>(0x00001L, "查询订单信息", inventory);
    }
}
