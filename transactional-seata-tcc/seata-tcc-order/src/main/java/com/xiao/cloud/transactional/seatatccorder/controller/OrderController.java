package com.xiao.cloud.transactional.seatatccorder.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccAccount;
import com.xiao.cloud.cloudcommon.entity.TccOrder;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import com.xiao.cloud.transactional.seatatccorder.service.OrderService;
import com.xiao.cloud.transactional.seatatccorder.tcc_api.AccountApi;
import com.xiao.cloud.transactional.seatatccorder.tcc_api.StorageApi;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @PostMapping("/produceOrder")
    @GlobalTransactional(rollbackFor = Exception.class)
    public CommonResult<TccOrder> produceOrder(TccOrder tccOrder) {
        orderService.save(tccOrder);
        CommonResult<TccStorage> resultStorage = storageApi.decreaseStorage(tccOrder.getStorageId(), 1L);
        log.info("库存信息 >>> {} ", resultStorage.getData());
        CommonResult<TccAccount> resultAccount = accountApi.deductionAmount(tccOrder.getAccountId(), new BigDecimal(4999));
        log.info("账号信息 >>> {} ", resultAccount.getData());
        //模拟业务异常
//        int i = 1 / 0;
        return new CommonResult<>(0x00001L, "订单生成成功", tccOrder);
    }
}
