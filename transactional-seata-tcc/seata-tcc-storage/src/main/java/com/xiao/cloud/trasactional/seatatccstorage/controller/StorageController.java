package com.xiao.cloud.trasactional.seatatccstorage.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import com.xiao.cloud.trasactional.seatatccstorage.service.StorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:40:47
 * @description
 */
@RestController
@RequestMapping("/tcc/storage")
@Slf4j
public class StorageController {

    @Value("${server.port}")
    private String port;
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 添加库存商品信息
     *
     * @param tccStorage
     *         库存商品
     * @return 返回库存信息
     */
    @PostMapping("/addStorage")
    public CommonResult<TccStorage> addStorage(TccStorage tccStorage) {
        storageService.save(tccStorage);
        return new CommonResult<>(0x00001L, "添加成功", tccStorage);
    }

    /**
     * 减少库存
     *
     * @param storageId
     *         库存商品ID
     * @param storageCount
     *         扣除数量
     * @return 扣除后的数据
     */
    @PostMapping("/decreaseStorage")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<TccStorage> decreaseStorage(@RequestParam(required = true) Long storageId, @RequestParam(required = true) Long storageCount) {
        TccStorage oldStorage = storageService.getById(storageId);

        int i = oldStorage.getStorageCount().compareTo(storageCount);
        Assert.isTrue(i != -1, "库存不足");
        oldStorage.setStorageCount(oldStorage.getStorageCount() - storageCount);
        storageService.updateById(oldStorage);
        return new CommonResult<>(0x00001L, "添加成功", oldStorage);
    }

    /**
     * 获取库存信息
     *
     * @param storageId
     *         库存ID
     * @return 返回库存信息
     */
    @GetMapping("/getStorageById/{id}")
    @Transactional(readOnly = true)
    public CommonResult<TccStorage> getStorageById(@PathVariable("id") Long storageId) {
        log.info(" 全局事务ID >>> {} ", RootContext.getXID());
        log.info(" 请求访问的端口 >>> {} ", port);
        TccStorage data = storageService.getById(storageId);
        return new CommonResult<>(Long.parseLong(port), "获取库存", data);
    }
}
