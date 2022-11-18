package com.xiao.cloud.transactional.seatatccorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.seata_tcc.order.entity.TccOrder;
import com.xiao.cloud.transactional.seatatccorder.mapper.OrderMapper;
import com.xiao.cloud.transactional.seatatccorder.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:14:39
 * @description
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TccOrder> implements OrderService {
}
