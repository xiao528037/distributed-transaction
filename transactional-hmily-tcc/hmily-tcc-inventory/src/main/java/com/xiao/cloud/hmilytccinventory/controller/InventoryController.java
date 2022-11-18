package com.xiao.cloud.hmilytccinventory.controller;

import com.xiao.cloud.hmilytccinventory.service.InventoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
