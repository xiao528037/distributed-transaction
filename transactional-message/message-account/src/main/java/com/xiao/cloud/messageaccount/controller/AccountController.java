package com.xiao.cloud.messageaccount.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;
import com.xiao.cloud.messageaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:37:32
 * @description
 */
@RestController
@RequestMapping("/message/account")
@Slf4j(topic = "AccountController")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deduction")
    public CommonResult deduction(String username, BigDecimal amount) {
        log.info(">>> 用户 {} 金额 {} ", username, amount);
        accountService.recharge(username, amount);
        return new CommonResult(0x000001L, "成功", null);
    }

    @GetMapping("/getStatus")
    public CommonResult<TbTx> getTx(String transactionalId) {
        TbTx tbTx = accountService.getTx(transactionalId);
        return new CommonResult<>(0x00001L, "success", tbTx);
    }
}
