package com.xiao.cloud.hmilytccorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.entity.HmilyTccOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:14:42
 * @description
 */
@Mapper
public interface OrderMapper extends BaseMapper<HmilyTccOrder> {
}
