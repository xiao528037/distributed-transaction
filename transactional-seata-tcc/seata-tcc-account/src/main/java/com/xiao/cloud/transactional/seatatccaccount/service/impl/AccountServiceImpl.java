package com.xiao.cloud.transactional.seatatccaccount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao.cloud.cloudcommon.seata_tcc.account.entity.TccAccount;
import com.xiao.cloud.transactional.seatatccaccount.mapper.AccountMapper;
import com.xiao.cloud.transactional.seatatccaccount.service.AccountService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 22:40:56
 * @description
 */
@Service("accountService")
@Slf4j(topic = "accountServiceImpl")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, TccAccount> implements AccountService {

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public TccAccount deductionAmount(Long accountId, BigDecimal deductionAmount) {
        TccAccount account = this.getById(accountId);
        if (account.getUserOver().compareTo(deductionAmount) != -1) {
            //余额足够，冻结资金
            account.setFrozenOver(deductionAmount);
        } else {
            throw new RuntimeException(account.getUsername() + "账号的余额不足");
        }
        this.updateById(account);
        return account;
    }

    @Override
    public boolean deductionCommit(BusinessActionContext context) {
        TccAccount account = this.getById(context.getActionContext("accountId", Long.class));
        if (account != null) {
            //余额减去冻结的资金
            account.setUserOver(account.getUserOver().subtract(account.getFrozenOver()));
            //冻结资金清零
            account.setFrozenOver(new BigDecimal(0));
            this.saveOrUpdate(account);
        }
        log.info("账户 {} >>> 全局事务ID > {} <<< 余额扣减 >成功< ", account.getUsername(), context.getXid());
        return true;
    }

    @Override
    public boolean deductionRollback(BusinessActionContext context) {
        TccAccount account = this.getById(context.getActionContext("accountId", Long.class));
        if (account != null) {
            //冻结金额清零
            account.setFrozenOver(new BigDecimal(0));
            this.saveOrUpdate(account);
        }
        log.info("账户 {} >>> 全局事务ID > {} <<< 余额扣减 >失败< ", account.getUsername(), context.getXid());
        return true;
    }
}
