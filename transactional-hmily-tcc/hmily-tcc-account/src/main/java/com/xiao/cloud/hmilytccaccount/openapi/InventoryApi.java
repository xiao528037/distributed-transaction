package com.xiao.cloud.hmilytccaccount.openapi;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.dto.InventoryDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:55:17
 * @description
 */
@FeignClient(name = "hmily-tcc-inventory-service", path = "/hmily/inventory")
public interface InventoryApi {

    /**
     * 扣减库存-正常
     *         库存ID
     * @param inventoryDTO
     *         扣减信息
     * @return 扣减后的库存信息
     */
    @Hmily
    @PostMapping("/decrease")
    CommonResult<HmilyTccInventory> decrease(@RequestBody InventoryDTO inventoryDTO);

    /**
     * 扣减库存-异常
     *         库存ID
     * @param inventoryDTO
     *         扣减信息
     * @return 扣减后的库存信息
     */
    @PostMapping("/decreaseException")
    CommonResult<HmilyTccInventory> decreaseException(@RequestBody InventoryDTO inventoryDTO);

    /**
     * 扣减库存-超时
     *         库存ID
     * @param inventoryDTO
     *         扣减信息
     * @return 扣减后的库存信息
     */
    @PostMapping("/decreaseTimeout")
    CommonResult<HmilyTccInventory> decreaseTimeout(@RequestBody InventoryDTO inventoryDTO);

    /**
     * 获取库存信息
     *
     * @param productId
     *         库存ID
     * @return 库存信息
     */
    @GetMapping("/get/{productId}")
    CommonResult<HmilyTccInventory> getInventoryById(@PathVariable("productId") String productId);
}