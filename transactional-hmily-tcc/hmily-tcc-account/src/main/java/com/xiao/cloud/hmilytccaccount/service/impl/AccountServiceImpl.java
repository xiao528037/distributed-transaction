package com.xiao.cloud.hmilytccaccount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import com.xiao.cloud.hmilytccaccount.mapper.AccountMapper;
import com.xiao.cloud.hmilytccaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.core.context.HmilyContext;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.dromara.hmily.core.context.HmilyTransactionContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:51:05
 * @description
 */
@Service("accountService")
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, HmilyTccAccount> implements AccountService {

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public HmilyTccAccount deductionBalance(Long accountId, Long mount) {
        Long transId = HmilyContextHolder.get().getTransId();
        log.info("事务ID >>>>>>>>>>>>>>>> {} ", transId);
        HmilyTccAccount account = this.getById(accountId);
        Assert.notNull(account, "未查询到用户信息");
        if (account.getBalance() >= mount) {
            account.setFreezeAmount(mount);
            this.saveOrUpdate(account);
            //模拟扣除失败
            int i = 1 / 0;
        } else {
            throw new RuntimeException(account.getUserId() + "账户余额不足");
        }
        return null;
    }

    @Override
    public HmilyTccAccount commit(Long accountId, Long mount) {
        Long transId = HmilyContextHolder.get().getTransId();
        log.info("提交 事务ID >>>>>>>>>>>>>>>> {} ", transId);
        HmilyTccAccount account = this.getById(accountId);
        if (account != null) {
            account.setBalance(account.getBalance() - mount);
            account.setFreezeAmount(account.getFreezeAmount() - mount);
            this.saveOrUpdate(account);
            return account;
        }
        return null;
    }

    @Override
    public HmilyTccAccount rollback(Long accountId, Long mount) {
        Long transId = HmilyContextHolder.get().getTransId();
        log.info("回滚 事务ID >>>>>>>>>>>>>>>> {} ", transId);
        HmilyTccAccount account = this.getById(accountId);
        if (account != null) {
            account.setFreezeAmount(0L);
            this.saveOrUpdate(account);
            return account;
        }
        return null;
    }
}
