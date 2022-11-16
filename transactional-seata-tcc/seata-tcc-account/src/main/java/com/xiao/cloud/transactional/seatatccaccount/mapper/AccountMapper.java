package com.xiao.cloud.transactional.seatatccaccount.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xiao.cloud.cloudcommon.entity.TccAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 22:35:09
 * @description
 */

@Mapper
public interface AccountMapper  extends BaseMapper<TccAccount> {
}
