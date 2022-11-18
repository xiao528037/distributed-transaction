package com.xiao.cloud.hmilytccorder.openapi;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:55:17
 * @description
 */
@FeignClient(name = "hmily-tcc-inventory-service", path = "/hmily/inventory")
public interface InventoryApi {

    /**
     * 扣减库存
     *
     * @param inventoryId
     *         库存ID
     * @param deductionCount
     *         扣减数量
     * @return 扣减后的库存信息
     */
    @Hmily
    @PostMapping("/deduction")
    CommonResult<HmilyTccInventory> deductionInventory(@RequestParam("inventoryId") Long inventoryId,
                                                       @RequestParam("deductionCount") Integer deductionCount);

    /**
     * 获取库存信息
     *
     * @param inventoryId
     *         库存ID
     * @return 库存信息
     */
    @GetMapping("/get/{productId}")
    CommonResult<HmilyTccInventory> getInventoryById(@PathVariable("productId") String productId);
}
