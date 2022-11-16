package com.xiao.cloud.transactional.seatatccaccount.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.TccAccount;
import com.xiao.cloud.transactional.seatatccaccount.service.AccountService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public CommonResult<TccAccount> addAccount(TccAccount tccAccount) {

        boolean save = accountService.save(tccAccount);
        if (!save) {
            return new CommonResult<>(0x00001L, "处理失败", tccAccount);
        }
        return new CommonResult<>(0x00001L, "处理成功", tccAccount);
    }
}
