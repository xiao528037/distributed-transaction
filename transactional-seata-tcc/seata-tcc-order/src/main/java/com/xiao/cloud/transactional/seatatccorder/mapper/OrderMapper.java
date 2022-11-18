package com.xiao.cloud.transactional.seatatccorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.seata_tcc.order.entity.TccOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:13:34
 * @description
 */
@Mapper
public interface OrderMapper extends BaseMapper<TccOrder> {
}
