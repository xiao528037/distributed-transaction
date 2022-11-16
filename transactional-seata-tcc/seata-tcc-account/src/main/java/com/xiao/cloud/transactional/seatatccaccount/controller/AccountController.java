package com.xiao.cloud.transactional.seatatccaccount.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccAccount;
import com.xiao.cloud.transactional.seatatccaccount.service.AccountService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 22:41:49
 * @description
 */
@RestController
@RequestMapping("/tcc/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 添加账号信息
     *
     * @param tccAccount
     *         账号信息
     * @return 返回账号信息
     */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<TccAccount> addAccount(TccAccount tccAccount) {
        boolean save = accountService.save(tccAccount);
        if (!save) {
            return new CommonResult<>(0x00001L, "处理失败", tccAccount);
        }
        return new CommonResult<>(0x00001L, "处理成功", tccAccount);
    }

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
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<TccAccount> decreaseAccountOver(@RequestParam(required = true) Long accountId, @RequestParam(required = true) BigDecimal over) {
        //获取当前账号余额
        TccAccount oldAccount = accountService.getById(accountId);
        BigDecimal subtract = oldAccount.getUserOver().subtract(over);
        int i = subtract.compareTo(new BigDecimal(0));
        Assert.isTrue(i != -1, "账号余额不足");
        TccAccount tccAccount = new TccAccount();
        tccAccount.setId(accountId);
        tccAccount.setUserOver(subtract);
        accountService.updateById(tccAccount);
        return new CommonResult<>(0x00001L, "处理成功", tccAccount);
    }

    /**
     * 通过账号ID获取信息
     *
     * @param accountId
     *         账号ID
     * @return 账号信息
     */
    @GetMapping("/get/{accountId}")
    @Transactional(readOnly = true)
    public CommonResult<TccAccount> getAccount(@PathVariable("accountId") Long accountId) {
        return new CommonResult<>(0x00001L, "处理成功", accountService.getById(accountId));
    }
}
