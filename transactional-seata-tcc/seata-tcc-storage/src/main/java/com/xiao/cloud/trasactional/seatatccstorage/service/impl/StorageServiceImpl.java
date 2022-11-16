package com.xiao.cloud.trasactional.seatatccstorage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import com.xiao.cloud.trasactional.seatatccstorage.mapper.StorageMapper;
import com.xiao.cloud.trasactional.seatatccstorage.service.StorageService;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:40:09
 * @description
 */
@Service("storageService")
public class StorageServiceImpl extends ServiceImpl<StorageMapper, TccStorage> implements StorageService {
}
