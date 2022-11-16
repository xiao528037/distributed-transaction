package com.xiao.cloud.trasactional.seatatccstorage.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import com.xiao.cloud.trasactional.seatatccstorage.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:40:47
 * @description
 */
@RestController
@RequestMapping("/tcc/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/addStorage")
    public CommonResult<TccStorage> addStorage(TccStorage tccStorage) {
        storageService.save(tccStorage);
        return new CommonResult<>(0x00001L, "添加成功", tccStorage);
    }
}
