package com.xiao.cloud.messageaccount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.message.account.dto.AccountDto;
import com.xiao.cloud.cloudcommon.message.account.entity.TbAccount;
import com.xiao.cloud.cloudcommon.message.account.mapper.AccountMapper;
import com.xiao.cloud.cloudcommon.message.recharge.dto.RechargeDto;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;
import com.xiao.cloud.messageaccount.api.RechargeApi;
import com.xiao.cloud.messageaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:18:13
 * @description
 */
@Service("accountService")
@Slf4j(topic = "AccountServiceImpl")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, TbAccount> implements AccountService {

    private final AccountMapper accountMapper;

    private final RechargeApi rechargeApi;

    public AccountServiceImpl(AccountMapper accountMapper, RechargeApi rechargeApi) {
        this.accountMapper = accountMapper;
        this.rechargeApi = rechargeApi;
    }

    @Override
    public void recharge(String username, BigDecimal amount) {
        RechargeDto rechargeDto = buildRechargeDto(username, amount);
        //增加积分
        rechargeApi.add(rechargeDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductionBalance(AccountDto accountDto) {
        if (accountMapper.isExist(accountDto.getTransactionalId()) > 0) {
            log.info("{} ", "此事务已经提交无需重复操作");
            return;
        }
        int i = 1 / 0;
        accountDto.setAmount(accountDto.getAmount().multiply(new BigDecimal(-1)));
        accountMapper.modifyBalance(accountDto);
        accountMapper.addTransactional(accountDto);
        log.info("{} ", "最大努力消息消费成功");
    }

    @Override
    public TbTx getTx(String transactionalId) {
        CommonResult<TbTx> result = rechargeApi.getTbTx(transactionalId);
        TbTx tbtx = null;
        if ((tbtx = result.getData()).getIsSuccess()) {
            AccountDto accountDto = buildAccountDto(tbtx.getUsername(), tbtx.getAmount());
            accountMapper.modifyBalance(accountDto);
            accountDto.setTransactionalId(tbtx.getTxid());
            accountMapper.addTransactional(accountDto);
        }
        return result.getData();
    }

    private AccountDto buildAccountDto(String username, BigDecimal amount) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAmount(amount);
        accountDto.setUsername(username);
        return accountDto;
    }

    private RechargeDto buildRechargeDto(String username, BigDecimal amount) {
        RechargeDto rechargeDto = new RechargeDto();
        rechargeDto.setIntegral(amount.multiply(new BigDecimal(10)));
        rechargeDto.setUsername(username);
        rechargeDto.setTransactionalId(UUID.randomUUID().toString());
        return rechargeDto;
    }
}
