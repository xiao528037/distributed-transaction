package com.xiao.cloud.trasactional.seatatccstorage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import com.xiao.cloud.trasactional.seatatccstorage.mapper.StorageMapper;
import com.xiao.cloud.trasactional.seatatccstorage.service.StorageService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:40:09
 * @description
 */
@Service("storageService")
@Slf4j(topic = "storageServiceImpl")
public class StorageServiceImpl extends ServiceImpl<StorageMapper, TccStorage> implements StorageService {
    @Override
    public TccStorage decreaseStorage(Long storageId, Long decreaseCount) {
        TccStorage storage = this.getById(storageId);
        if (storage != null && storage.getStorageCount() >= decreaseCount) {
            //冻结库存
            storage.setFrozenCount(decreaseCount);
        } else {
            throw new RuntimeException(storageId + "扣减库存失败");
        }
        this.saveOrUpdate(storage);
        return storage;
    }

    @Override
    public boolean storageCommit(BusinessActionContext context) {
        TccStorage storage = this.getById(context.getActionContext("storageId", Long.class));
        if (storage != null) {
            //扣减库存，减去本次交易冻结库存数量
            storage.setStorageCount(storage.getStorageCount() - storage.getFrozenCount());
            storage.setFrozenCount(0L);
            this.saveOrUpdate(storage);
        }
        log.info("库存扣减成功 >>> 全局事务ID >>> {} ", context.getXid());
        return true;
    }

    @Override
    public boolean storageRollback(BusinessActionContext context) {
        TccStorage storage = this.getById(context.getActionContext("storageId", Long.class));
        if (storage != null) {
            storage.setFrozenCount(0L);
            this.saveOrUpdate(storage);
        }
        log.info("库存扣减失败 >>> 全局事务ID >>> {} ", context.getXid());
        return true;
    }
}
