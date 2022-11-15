package com.xiao.cloud.cloudalibabaseataprovicerstock8001.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.entity.PhoneStock;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-29 20:47:07
 * @description
 */
@Mapper
public interface StockMapper extends BaseMapper<PhoneStock> {
}
