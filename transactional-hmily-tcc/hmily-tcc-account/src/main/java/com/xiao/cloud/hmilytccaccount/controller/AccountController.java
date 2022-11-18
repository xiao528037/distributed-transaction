package com.xiao.cloud.hmilytccaccount.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import com.xiao.cloud.hmilytccaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<HmilyTccAccount> deduction(@RequestParam("accountId") Long accountId,@RequestParam("mount") Long mount) {
        HmilyTccAccount account = accountService.deductionBalance(accountId, mount);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @GetMapping("/get/{userId}")
    public CommonResult<HmilyTccAccount> getAccount(@PathVariable("userId") String userId) {
        QueryWrapper<HmilyTccAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HmilyTccAccount::getUserId,userId);
        HmilyTccAccount account = accountService.getOne(wrapper);
        Assert.notNull(account, "未查询到用户信息");
        return new CommonResult<>(0x00001L, "处理成功", account);
    }
}
