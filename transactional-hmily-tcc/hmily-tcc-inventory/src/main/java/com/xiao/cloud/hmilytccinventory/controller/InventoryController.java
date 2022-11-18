package com.xiao.cloud.hmilytccinventory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.dto.InventoryDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import com.xiao.cloud.hmilytccinventory.service.InventoryService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 09:42:39
 * @description 库存接口
 */
@RestController
@RequestMapping("/hmily/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/decrease")
    public CommonResult<HmilyTccInventory> decrease(@RequestBody InventoryDTO inventoryDTO) {
        HmilyTccInventory inventory = inventoryService.decreaseInventory(inventoryDTO);
        return new CommonResult<>(0x00001L, "库存扣减成功", inventory);
    }

    @PostMapping("/decreaseException")
    public CommonResult<HmilyTccInventory> decreaseException(@RequestBody InventoryDTO inventoryDTO) {
        HmilyTccInventory hmilyTccInventory = inventoryService.decreaseInventoryException(inventoryDTO);
        return new CommonResult<>(0x00001L, "库存扣减成功", hmilyTccInventory);
    }

    @PostMapping("/decreaseTimeout")
    public CommonResult<HmilyTccInventory> decreaseTimeout(@RequestBody InventoryDTO inventoryDTO) {
        HmilyTccInventory hmilyTccInventory = inventoryService.decreaseInventoryTimeout(inventoryDTO);
        return new CommonResult<>(0x00001L, "库存扣减成功", hmilyTccInventory);
    }

    @GetMapping("/get/{productId}")
    public CommonResult<HmilyTccInventory> getInventoryById(@PathVariable("productId") String productId) {
        QueryWrapper<HmilyTccInventory> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HmilyTccInventory::getProductId, productId);
        HmilyTccInventory inventory = inventoryService.getOne(wrapper);
        Assert.notNull(inventory, "未查询到相关库存信息");
        return new CommonResult<>(0x00001L, "查询库存成功", inventory);
    }

}
