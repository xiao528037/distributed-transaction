package com.xiao.cloud.hmilytccorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.enums.OrderStatusEnum;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.mapper.OrderMapper;
import com.xiao.cloud.hmilytccorder.openapi.AccountApi;
import com.xiao.cloud.hmilytccorder.openapi.InventoryApi;
import com.xiao.cloud.hmilytccorder.service.OrderService;
import com.xiao.cloud.hmilytccorder.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.common.utils.IdWorkerUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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

    private final PaymentService paymentService;

    public OrderServiceImpl(OrderMapper orderMapper,
                            AccountApi accountApi,
                            InventoryApi inventoryApi,
                            PaymentService paymentService) {
        this.orderMapper = orderMapper;
        this.accountApi = accountApi;
        this.inventoryApi = inventoryApi;
        this.paymentService = paymentService;
    }


    @Override
    public HmilyTccOrder orderPay(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        long start = System.currentTimeMillis();
        paymentService.makePayment(hmilyTccOrder);
        System.out.println("分布式事务耗时：" + (System.currentTimeMillis() - start));
        return hmilyTccOrder;
    }

    @Override
    public HmilyTccOrder inventoryTryException(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        paymentService.makePaymentInventoryTryException(hmilyTccOrder);
        return hmilyTccOrder;
    }

    @Override
    public HmilyTccOrder accountTryException(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        paymentService.makePaymentAccountTryException(hmilyTccOrder);
        return hmilyTccOrder;
    }

    @Override
    public HmilyTccOrder inventoryTryTimeout(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        paymentService.makePaymentInventoryTryTimeout(hmilyTccOrder);
        return hmilyTccOrder;
    }

    @Override
    public HmilyTccOrder accountTryTimeout(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        paymentService.makePaymentAccountTryTimeout(hmilyTccOrder);
        return hmilyTccOrder;
    }

    @Override
    public HmilyTccOrder orderPayNested(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        paymentService.makePaymentNested(hmilyTccOrder);
        return hmilyTccOrder;
    }

    @Override
    public HmilyTccOrder orderPayNestedException(Integer count, BigDecimal amount) {
        HmilyTccOrder hmilyTccOrder = saveOrder(count, amount);
        paymentService.makePaymentNestedException(hmilyTccOrder);
        return hmilyTccOrder;
    }

    private HmilyTccOrder saveOrder(Integer count, BigDecimal amount) {
        final HmilyTccOrder order = buildOrder(count, amount);
        orderMapper.save(order);
        return order;
    }

    private HmilyTccOrder buildOrder(Integer count, BigDecimal amount) {
        HmilyTccOrder order = new HmilyTccOrder();
        order.setCreateTime(new Date());
        order.setNumber(String.valueOf(IdWorkerUtils.getInstance().createUUID()));
        //demo中的表里只有商品id为 1的数据
        order.setProductId("1");
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中 表里面存的用户id为10000
        order.setUserId("10000");
        return order;
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
