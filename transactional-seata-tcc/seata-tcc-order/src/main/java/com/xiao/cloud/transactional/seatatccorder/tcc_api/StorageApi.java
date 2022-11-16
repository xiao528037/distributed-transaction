package com.xiao.cloud.transactional.seatatccorder.tcc_api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-16 14:03:54
 * @description
 */
@FeignClient(name = "tcc-storage-service", path = "/tcc/storage")
public interface StorageApi {

    /**
     * 添加库存商品信息
     *
     * @param tccStorage
     *         库存商品
     * @return 返回库存信息
     */
    @PostMapping("/addStorage")
    CommonResult<TccStorage> addStorage(TccStorage tccStorage);

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
    CommonResult<TccStorage> decreaseStorage(@RequestParam(value = "storageId", required = true) Long storageId,
                                             @RequestParam(value = "storageCount", required = true) Long storageCount);

    /**
     * 获取库存信息
     *
     * @param storageId
     *         库存ID
     * @return 返回库存信息
     */
    @GetMapping("/getStorageById/{id}")
    CommonResult<TccStorage> getStorageById(@PathVariable("id") Long storageId);
}
