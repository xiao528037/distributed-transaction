package com.xiao.cloud.cloudalibabaseataorder80.api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.seata_at.stock.entity.PhoneStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 12:58:31
 * @description
 */

@FeignClient(name = "provider-stock", path = "/stock")
public interface StockApi {

    @PostMapping("/add")
    public CommonResult<PhoneStock> add(PhoneStock phoneStock);

    @DeleteMapping("/delete")
    public CommonResult<PhoneStock> delete(Long id);

    @GetMapping("/get/{id}")
    public CommonResult<PhoneStock> get(@PathVariable("id") Long id);

    @PostMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Boolean> update(@RequestBody PhoneStock phoneStock);
}
