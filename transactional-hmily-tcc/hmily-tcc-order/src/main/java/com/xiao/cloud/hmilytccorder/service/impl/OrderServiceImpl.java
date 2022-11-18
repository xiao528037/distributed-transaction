package com.xiao.cloud.hmilytccorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.entity.HmilyTccOrder;
import com.xiao.cloud.hmilytccorder.mapper.OrderMapper;
import com.xiao.cloud.hmilytccorder.openapi.AccountApi;
import com.xiao.cloud.hmilytccorder.openapi.InventoryApi;
import com.xiao.cloud.hmilytccorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:24:37
 * @description
 */
@Service("orderService")
@Slf4j(topic = "OrderServiceImpl")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, HmilyTccOrder> implements OrderService {

    private final OrderMapper orderMapper;

    private final AccountApi accountApi;

    private final InventoryApi inventoryApi;

    public OrderServiceImpl(OrderMapper orderMapper,
                            AccountApi accountApi,
                            InventoryApi inventoryApi) {
        this.orderMapper = orderMapper;
        this.accountApi = accountApi;
        this.inventoryApi = inventoryApi;
    }


    @Override
    public HmilyTccOrder addOrder(HmilyTccOrder hmilyTccOrder) {
        /**
         * 查询账户信息
         */
        CommonResult<HmilyTccAccount> accountResult = accountApi.getAccount(hmilyTccOrder.getUserId());
        HmilyTccAccount account = accountResult.getData();
        /**
         * 查询库存信息
         */
        CommonResult<HmilyTccInventory> orderResult = inventoryApi.getInventoryById(hmilyTccOrder.getProductId());
        HmilyTccInventory inventory = orderResult.getData();
        /**
         * 扣减库存
         */
//        accountApi.deduction(account.getId(),)
        /**
         * 扣减账户余额
         */
        /**
         * 生成订单
         */
        return null;
    }

    @Override
    public HmilyTccOrder commit(HmilyTccOrder hmilyTccOrder) {
        return null;
    }

    @Override
    public HmilyTccOrder rollback(HmilyTccOrder hmilyTccOrder) {
        return null;
    }

    @Override
    public HmilyTccAccount getAccountById(String userId) {
        CommonResult<HmilyTccAccount> result = accountApi.getAccount(userId);
        HmilyTccAccount data = result.getData();
        return data;
    }

    @Override
    public HmilyTccInventory getInventoryById(String productId) {
        CommonResult<HmilyTccInventory> result = inventoryApi.getInventoryById(productId);
        HmilyTccInventory inventory = result.getData();
        return inventory;
    }
}
