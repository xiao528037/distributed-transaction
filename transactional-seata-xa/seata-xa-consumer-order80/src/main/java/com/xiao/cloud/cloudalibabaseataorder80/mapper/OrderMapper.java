package com.xiao.cloud.cloudalibabaseataorder80.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.xiao.cloud.cloudcommon.entity.OrderList;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 14:10:08
 * @description
 */

@Mapper
public interface OrderMapper extends BaseMapper<OrderList> {
}
