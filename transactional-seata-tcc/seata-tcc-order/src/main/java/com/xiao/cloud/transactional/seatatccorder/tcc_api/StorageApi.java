package com.xiao.cloud.transactional.seatatccorder.tcc_api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.seata_tcc.storage.entity.TccStorage;
import org.springframework.cloud.openfeign.FeignClient;
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
     * 扣减库存
     *
     * @param storageId
     *         库存ID
     * @param decreaseCount
     *         扣减数量
     * @return 扣减后的信息
     */
    @PostMapping("/decrease")
    CommonResult<TccStorage> decreaseStorage(@RequestParam(value = "storageId", required = true) Long storageId,
                                        @RequestParam(value = "decreaseCount", required = true) Long decreaseCount);
}
