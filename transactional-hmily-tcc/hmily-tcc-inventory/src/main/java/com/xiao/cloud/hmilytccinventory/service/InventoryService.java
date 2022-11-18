package com.xiao.cloud.hmilytccinventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 09:40:41
 * @description
 */
public interface InventoryService extends IService<HmilyTccInventory> {

    /**
     * 扣除
     *
     * @param inventoryId
     *         库存商品ID
     * @param deductionCount
     *         扣除数量
     * @return 返回扣减后的数量
     */
    @HmilyTCC(confirmMethod = "commit", cancelMethod = "rollback")
    @Transactional(rollbackFor = Exception.class)
    HmilyTccInventory deductionInventory(Long inventoryId, Integer deductionCount);

    /**
     * 扣除
     *
     * @param inventoryId
     *         库存商品ID
     * @param deductionCount
     *         扣除数量
     * @return 返回扣减后的数量
     */
    HmilyTccInventory commit(Long inventoryId, Integer deductionCount);

    /**
     * 扣除
     *
     * @param inventoryId
     *         库存商品ID
     * @param deductionCount
     *         扣除数量
     * @return 返回扣减后的数量
     */
    HmilyTccInventory rollback(Long inventoryId, Integer deductionCount);
}
