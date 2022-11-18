package com.xiao.cloud.hmilytccorder.openapi;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:55:06
 * @description
 */
@FeignClient(name = "hmily-tcc-account-service", path = "/hmily/account")
public interface AccountApi {

    /**
     * 扣减余额
     *
     * @param accountId
     *         用户ID
     * @param deductionAmount
     *         扣减余额
     * @return 扣减信息
     */
    @Hmily
    @PostMapping("/deduction")
    CommonResult<HmilyTccAccount> deduction(@RequestParam("accountId") String accountId, @RequestParam("mount") BigDecimal deductionAmount);

    /**
     * 用户信息
     *
     * @param accountId
     *         用户ID
     * @return 用户信息
     */
    @GetMapping("/get/{userId}")
    CommonResult<HmilyTccAccount> getAccount(@PathVariable("userId") String userId);
}
