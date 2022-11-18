package com.xiao.cloud.cloudalibabaseataorder80.api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.seata_at.commodity.entity.CommodityDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 15:13:27
 * @description
 */
@FeignClient(name = "provider-commodity", path = "/commodity")
public interface CommodityApi {

    @GetMapping("/get/{id}")
    public CommonResult<CommodityDetails> get(@PathVariable("id") Long id);
}
