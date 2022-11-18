package com.xiao.cloud.transactional.seatatccaccount.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.seata_tcc.account.entity.TccAccount;
import com.xiao.cloud.transactional.seatatccaccount.service.AccountService;
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

    @PostMapping("/deduction")
    public CommonResult<TccAccount> deductionAmount(@RequestParam(value = "accountId", required = true) Long accountId,
                                                    @RequestParam(value = "deductionAmount", required = true) BigDecimal deductionAmount) {
        TccAccount account = accountService.deductionAmount(accountId, deductionAmount);
        //模拟业务异常
//        int i = 1 / 0;
        return new CommonResult<>(0x00001L, "余额扣减成功", account);
    }
}
