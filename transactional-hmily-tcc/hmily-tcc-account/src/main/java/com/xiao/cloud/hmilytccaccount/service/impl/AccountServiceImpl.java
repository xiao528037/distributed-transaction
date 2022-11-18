package com.xiao.cloud.hmilytccaccount.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public HmilyTccAccount deductionBalance(String userId, BigDecimal deductionAmount) {
        Long transId = HmilyContextHolder.get().getTransId();
        log.info("事务ID >>>>>>>>>>>>>>>> {} ", transId);
        QueryWrapper<HmilyTccAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HmilyTccAccount::getUserId, userId);
        HmilyTccAccount account = this.getOne(wrapper);
        Assert.notNull(account, "未查询到用户信息");
        if (account.getBalance().compareTo(deductionAmount) != -1) {
            account.setFreezeAmount(deductionAmount);
            this.saveOrUpdate(account);
            //模拟扣除失败
            //int i = 1 / 0;
        } else {
            throw new RuntimeException(account.getUserId() + "账户余额不足");
        }
        return account;
    }

    @Override
    public HmilyTccAccount commit(String userId, BigDecimal deductionAmount) {
        Long transId = HmilyContextHolder.get().getTransId();
        log.info("提交 事务ID >>>>>>>>>>>>>>>> {} ", transId);
        QueryWrapper<HmilyTccAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HmilyTccAccount::getUserId, userId);
        HmilyTccAccount account = this.getOne(wrapper);
        if (account != null) {
            account.setBalance(account.getBalance().subtract(deductionAmount));
            account.setFreezeAmount(account.getFreezeAmount().subtract(deductionAmount));
            this.saveOrUpdate(account);
            return account;
        }
        return null;
    }

    @Override
    public HmilyTccAccount rollback(String userId, BigDecimal mount) {
        Long transId = HmilyContextHolder.get().getTransId();
        log.info("回滚 事务ID >>>>>>>>>>>>>>>> {} ", transId);
        QueryWrapper<HmilyTccAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HmilyTccAccount::getUserId, userId);
        HmilyTccAccount account = this.getOne(wrapper);
        if (account != null) {
            account.setFreezeAmount(new BigDecimal(0));
            this.saveOrUpdate(account);
            return account;
        }
        return null;
    }
}
