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

    @PostMapping("/decrease")
    public CommonResult<TccStorage> decreaseStorage(@RequestParam(value = "storageId", required = true) Long storageId,
                                                    @RequestParam(value = "decreaseCount", required = true) Long decreaseCount) {
        TccStorage storage = storageService.decreaseStorage(storageId, decreaseCount);
        //模拟业务异常
//        int i = 1 / 0;
        return new CommonResult<>(0x00001L, "扣减库存成功", storage);
    }
}
