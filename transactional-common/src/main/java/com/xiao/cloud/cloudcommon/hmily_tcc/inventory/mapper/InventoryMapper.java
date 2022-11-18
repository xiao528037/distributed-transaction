package com.xiao.cloud.cloudcommon.hmily_tcc.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.dto.InventoryDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 09:40:54
 * @description
 */
@Mapper
public interface InventoryMapper extends BaseMapper<HmilyTccInventory> {
    /**
     * 冻结库存
     *
     * @param inventoryDTO
     * @return 更新数量
     */
    @Update("UPDATE hmily_tcc_stock.hmily_tcc_inventory SET total_inventory = total_inventory - #{count,jdbcType=INTEGER}," +
            "lock_inventory = #{count,jdbcType=INTEGER} WHERE product_id = #{productId,jdbcType=VARCHAR} AND " +
            "lock_inventory >= #{count,jdbcType=INTEGER}")
    int decrease(InventoryDTO inventoryDTO);

    /**
     * 真实扣减库存
     *
     * @param inventoryDTO
     * @return 更新数量
     */
    @Update("UPDATE hmily_tcc_stock.hmily_tcc_inventory SET lock_inventory = lock_inventory - #{count,jdbcType=INTEGER} " +
            "WHERE product_id = #{productId,jdbcType=VARCHAR} AND lock_inventory >= #{count,jdbcType=INTEGER}")
    int commit(InventoryDTO inventoryDTO);

    /**
     * 事务回滚
     *
     * @param inventoryDTO
     * @return 更新数量
     */
    @Update("UPDATE hmily_tcc_stock.hmily_tcc_inventory SET total_inventory = total_inventory + #{count,jdbcType=INTEGER}," +
            "lock_inventory = lock_inventory - #{count,jdbcType=INTEGER} WHERE product_id = #{productId,jdbcType=VARCHAR}" +
            " AND lock_inventory >= #{count,jdbcType=INTEGER}")
    int rollback(InventoryDTO inventoryDTO);
}
