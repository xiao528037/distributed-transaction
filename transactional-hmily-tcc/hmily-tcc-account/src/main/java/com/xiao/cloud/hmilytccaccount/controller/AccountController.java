package com.xiao.cloud.hmilytccaccount.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountNestedDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.hmilytccaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @PostMapping("/payment")
    public CommonResult<HmilyTccAccount> payment(@RequestBody AccountDTO accountDTO) {
        HmilyTccAccount account = accountService.payment(accountDTO);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @PostMapping("/paymentException")
    public CommonResult<HmilyTccAccount> paymentException(@RequestBody AccountDTO accountDTO) {
        HmilyTccAccount account = accountService.paymentWithTryException(accountDTO);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @PostMapping("/paymentTime")
    public CommonResult<HmilyTccAccount> paymentTimeout(@RequestBody AccountDTO accountDTO) {
        HmilyTccAccount account = accountService.paymentWithTryTimeOut(accountDTO);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @PostMapping("/paymentNested")
    public CommonResult<HmilyTccAccount> paymentWithNested(@RequestBody AccountNestedDTO accountNestedDTO) {
        HmilyTccAccount account = accountService.paymentWithNested(accountNestedDTO);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @PostMapping("/paymentNestedException")
    public CommonResult<HmilyTccAccount> paymentNestedException(@RequestBody AccountNestedDTO accountNestedDTO) {
        HmilyTccAccount account = accountService.paymentWithNestedException(accountNestedDTO);
        return new CommonResult<>(0x00001L, "处理成功", account);
    }

    @GetMapping("/get/{userId}")
    public CommonResult<HmilyTccAccount> getAccount(@PathVariable("userId") String userId) {
        QueryWrapper<HmilyTccAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HmilyTccAccount::getUserId, userId);
        HmilyTccAccount account = accountService.getOne(wrapper);
        Assert.notNull(account, "未查询到用户信息");
        return new CommonResult<>(0x00001L, "处理成功", account);
    }
}
