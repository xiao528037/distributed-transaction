package com.xiao.cloud.hmilytccinventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import com.xiao.cloud.hmilytccinventory.mapper.InventoryMapper;
import com.xiao.cloud.hmilytccinventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 09:41:42
 * @description
 */

@Service("inventoryService")
@Slf4j(topic = "InventoryServiceImpl")
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, HmilyTccInventory> implements InventoryService {
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public HmilyTccInventory deductionInventory(Long inventoryId, Integer deductionCount) {
        log.info(">>> Inventory模块,预留资源方法  全局事务ID  >>>  {} ", HmilyContextHolder.get().getTransId());
        HmilyTccInventory inventory = this.getById(inventoryId);
        Assert.notNull(inventory, "未查询到库存信息");
        if (inventory.getTotalInventory() >= deductionCount) {
            inventory.setLockInventory(deductionCount);
            this.saveOrUpdate(inventory);
        } else {
            throw new RuntimeException(inventoryId + "库存数量不足");
        }
        return inventory;
    }

    @Override
    public HmilyTccInventory commit(Long inventoryId, Integer deductionCount) {
        log.info(">>> Inventory模块,提交方法  全局事务ID  >>>  {} ", HmilyContextHolder.get().getTransId());
        HmilyTccInventory inventory = this.getById(inventoryId);
        inventory.setTotalInventory(inventory.getTotalInventory() - inventory.getLockInventory());
        inventory.setLockInventory(0);
        this.saveOrUpdate(inventory);
        return inventory;
    }

    @Override
    public HmilyTccInventory rollback(Long inventoryId, Integer deductionCount) {
        log.info(">>> Inventory模块,回滚方法  全局事务ID  >>>  {} ", HmilyContextHolder.get().getTransId());
        HmilyTccInventory inventory = this.getById(inventoryId);
        inventory.setLockInventory(0);
        this.saveOrUpdate(inventory);
        return inventory;
    }
}
