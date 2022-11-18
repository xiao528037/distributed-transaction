package com.xiao.cloud.hmilytccinventory.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import com.xiao.cloud.hmilytccinventory.service.InventoryService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 09:42:39
 * @description
 */
@RestController
@RequestMapping("/hmily/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/deduction")
    public CommonResult<HmilyTccInventory> deductionInventory(@RequestParam("inventoryId") Long inventoryId,@RequestParam("deductionCount") Integer deductionCount) {
        HmilyTccInventory inventory = inventoryService.deductionInventory(inventoryId, deductionCount);
        return new CommonResult<>(0x00001L, "库存扣减成功", inventory);
    }

    @GetMapping("/get/{inventoryId}")
    public CommonResult<HmilyTccInventory> getInventoryById(@PathVariable("inventoryId") Long inventoryId) {
        HmilyTccInventory inventory = inventoryService.getById(inventoryId);
        Assert.notNull(inventory, "未查询到相关库存信息");
        return new CommonResult<>(0x00001L, "查询库存成功", inventory);
    }

}
