package com.xiao.cloud.cloudalibabaseataorder80.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudalibabaseataorder80.mapper.OrderMapper;
import com.xiao.cloud.cloudalibabaseataorder80.service.OrderService;
import com.xiao.cloud.cloudcommon.seata_at.order.entity.OrderList;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 14:11:07
 * @description
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderList> implements OrderService {
}
