package com.xiao.cloud.cloudalibabaseataprovicerstock8001.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudalibabaseataprovicerstock8001.mapper.StockMapper;
import com.xiao.cloud.cloudalibabaseataprovicerstock8001.service.MyService;
import com.xiao.cloud.cloudcommon.seata_at.stock.entity.PhoneStock;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-29 20:44:32
 * @description
 */
@Service("myService")
public class MyServiceImpl extends ServiceImpl<StockMapper, PhoneStock> implements MyService {
}
