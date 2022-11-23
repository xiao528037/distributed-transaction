package com.xiao.cloud.rocketmqmessageaccount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.message_tx.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.message_tx.account.entity.MessageTxAccount;
import com.xiao.cloud.cloudcommon.message_tx.account.mapper.AccountMapper;
import com.xiao.cloud.rocketmqmessageaccount.service.AccountServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-22 18:41:41
 * @description
 */
@Service("accountServer")
@Slf4j(topic = "AccountServerImpl")
public class AccountServerImpl extends ServiceImpl<AccountMapper, MessageTxAccount> implements AccountServer {

    private final AccountMapper accountMapper;

    public AccountServerImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductionAmount(AccountDTO accountDTO, String productId, String transactionalId) {
        log.info("消费主体 >>> {} 事务ID >>>  {}", accountDTO, transactionalId);
        if (accountMapper.isExist(transactionalId) > 0) {
            log.info(">>> {} ", "重复消费");
            return;
        }
        int update = accountMapper.update(accountDTO);
        int saveTransactional = accountMapper.saveTransactional(productId, transactionalId);
        if (update <= 0 || saveTransactional <= 0) {
            throw new RuntimeException("消费失败");
        }
    }
}
