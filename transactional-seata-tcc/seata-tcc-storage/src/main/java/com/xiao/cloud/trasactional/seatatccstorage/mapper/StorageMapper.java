package com.xiao.cloud.trasactional.seatatccstorage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.seata_tcc.storage.entity.TccStorage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:38:24
 * @description
 */
@Mapper
public interface StorageMapper extends BaseMapper<TccStorage> {
}
