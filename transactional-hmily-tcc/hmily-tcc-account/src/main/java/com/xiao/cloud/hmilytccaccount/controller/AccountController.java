package com.xiao.cloud.hmilytccaccount.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import com.xiao.cloud.hmilytccaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:51:52
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/hmily/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/deduction")
    public CommonResult<HmilyTccAccount> deduction(Long accountId, Long mount) {
        HmilyTccAccount account = accountService.deductionBalance(accountId, mount);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @GetMapping("/get/{accountId}")
    public CommonResult<HmilyTccAccount> getAccount(Long accountId) {
        HmilyTccAccount account = accountService.getById(accountId);
        Assert.notNull(account, "未查询到库存信息");
        return new CommonResult<>(0x00001L, "处理成功", account);
    }
}
