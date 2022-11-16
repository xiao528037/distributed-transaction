package com.xiao.cloud.transactional.seatatccorder.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccOrder;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import com.xiao.cloud.transactional.seatatccorder.service.OrderService;
import com.xiao.cloud.transactional.seatatccorder.tcc_api.AccountApi;
import com.xiao.cloud.transactional.seatatccorder.tcc_api.StorageApi;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private final StorageApi storageApi;
    private final AccountApi accountApi;

    public OrderController(OrderService orderService, StorageApi storageApi, AccountApi accountApi) {
        this.orderService = orderService;
        this.storageApi = storageApi;
        this.accountApi = accountApi;
    }

    @PostMapping("/addOrder")
    @GlobalTransactional(rollbackFor = Exception.class)
    public CommonResult<TccOrder> addOrder(TccOrder tccOrder) {
        log.info("<<<< 当前事务ID >>>> {} ", RootContext.getXID());
        log.info(">>> 获取库存信息 <<< {} ", tccOrder.getStorageId());
        CommonResult<TccStorage> result = storageApi.getStorageById(tccOrder.getStorageId());
        TccStorage data = result.getData();
        log.info(">>> 扣除库存数量 <<< {} ", 1);
        storageApi.decreaseStorage(data.getId(), 1L);
        log.info(">>> 扣除账号余额 <<< {} ", tccOrder.getAccountId());
        accountApi.decreaseAccountOver(tccOrder.getAccountId(), data.getCommodityPrice());
        log.info(">>> 增加订单信息 <<< {} ", tccOrder);
        orderService.save(tccOrder);
        //假设业务出现异常需全局事务回滚
        int i = 1 / 0;
        return new CommonResult<>(0x00001L, "操作成功", tccOrder);
    }

    @GetMapping("/get/{id}")
    @GlobalTransactional(rollbackFor = Exception.class)
    public CommonResult<TccStorage> getStorage(@PathVariable("id") Long storageId) {
        CommonResult<TccStorage> result = storageApi.getStorageById(storageId);
        TccStorage data = result.getData();
        return new CommonResult<>(result.getCode(), "库存信息", data);
    }
}
