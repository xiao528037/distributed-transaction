package com.xiao.cloud.transactional.seatatccorder.tcc_api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 添加账号信息
     *
     * @param tccAccount
     *         账号信息
     * @return 返回账号信息
     */
    @PostMapping("/add")
    public CommonResult<TccAccount> addAccount(TccAccount tccAccount);

    /**
     * 扣除账号余额
     *
     * @param accountId
     *         账号ID
     * @param over
     *         扣除的余额
     * @return 扣除成功返回
     */
    @PostMapping("/update")
    CommonResult<TccAccount> decreaseAccountOver(@RequestParam(value = "accountId", required = true) Long accountId,
                                                 @RequestParam(value = "over", required = true) BigDecimal over);


    /**
     * 通过账号ID获取信息
     *
     * @param accountId
     *         账号ID
     * @return 账号信息
     */
    @GetMapping("/get/{accountId}")
    CommonResult<TccAccount> getAccount(@PathVariable("accountId") Long accountId);
}
