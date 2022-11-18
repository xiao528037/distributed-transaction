package com.xiao.cloud.hmilytccorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.entity.HmilyTccOrder;
import com.xiao.cloud.hmilytccorder.mapper.OrderMapper;
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

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public HmilyTccOrder addOrder(HmilyTccOrder hmilyTccOrder) {

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
}
