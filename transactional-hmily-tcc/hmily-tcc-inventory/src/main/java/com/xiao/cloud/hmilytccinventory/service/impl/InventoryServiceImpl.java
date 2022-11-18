package com.xiao.cloud.hmilytccinventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.dto.InventoryDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.mapper.InventoryMapper;
import com.xiao.cloud.hmilytccinventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

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
    public HmilyTccInventory decreaseInventory(InventoryDTO inventoryDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行库存接口try方法-正常", HmilyContextHolder.get().getTransId());
        HmilyTccInventory hmilyTccInventory = decrease(inventoryDTO);
        return hmilyTccInventory;
    }

    @Override
    public HmilyTccInventory decreaseInventoryException(InventoryDTO inventoryDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行库存接口try方法-异常", HmilyContextHolder.get().getTransId());
        throw new RuntimeException("扣除 >>> productId >> " + inventoryDTO.getProductId() + ",数量" + inventoryDTO.getCount() + " <<< 失败");
    }

    @Override
    public HmilyTccInventory decreaseInventoryTimeout(InventoryDTO inventoryDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行库存接口try方法-超时", HmilyContextHolder.get().getTransId());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HmilyTccInventory hmilyTccInventory = decrease(inventoryDTO);
        return hmilyTccInventory;
    }


    @Override
    public HmilyTccInventory commit(InventoryDTO inventoryDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行库存接口commit方法", HmilyContextHolder.get().getTransId());
        inventoryMapper.commit(inventoryDTO);
        HmilyTccInventory hmilyTccInventory = getHmilyTccInventory(inventoryDTO);
        return hmilyTccInventory;
    }

    @Override
    public HmilyTccInventory rollback(InventoryDTO inventoryDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行库存接口rollback方法", HmilyContextHolder.get().getTransId());
        inventoryMapper.rollback(inventoryDTO);
        HmilyTccInventory hmilyTccInventory = getHmilyTccInventory(inventoryDTO);
        return hmilyTccInventory;
    }


    /**
     * 扣减库存
     *
     * @param inventoryDTO
     * @return 库存信息
     */
    private HmilyTccInventory decrease(InventoryDTO inventoryDTO) {
        int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease <= 0) {
            throw new RuntimeException("扣除 >>> productId >> " + inventoryDTO.getProductId() + ",数量" + inventoryDTO.getCount() + " <<< 失败");
        }
        return getHmilyTccInventory(inventoryDTO);
    }

    /**
     * 查询库存信息
     *
     * @param inventoryDTO
     *         条件
     * @return 库存信息
     */
    private HmilyTccInventory getHmilyTccInventory(InventoryDTO inventoryDTO) {
        QueryWrapper<HmilyTccInventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HmilyTccInventory::getProductId, inventoryDTO.getProductId());
        HmilyTccInventory hmilyTccInventory = this.getOne(queryWrapper);
        return hmilyTccInventory;
    }
}
