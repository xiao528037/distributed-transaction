package com.xiao.cloud.transactional.seatatccorder.tcc_api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.seata_tcc.account.entity.TccAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-16 13:37:16
 * @description
 */
@FeignClient(name = "tcc-account-service", path = "/tcc/account")
public interface AccountApi {
    /**
     * 扣减金额
     *
     * @param accountId
     *         用户ID
     * @param deductionAmount
     *         扣减金额
     * @return 扣减后的信息
     */
    @PostMapping("/deduction")
     CommonResult<TccAccount> deductionAmount(@RequestParam(value = "accountId", required = true) Long accountId,
                                                    @RequestParam(value = "deductionAmount", required = true) BigDecimal deductionAmount);
}
