package com.xiao.cloud.transactional.seatatccaccount.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao.cloud.cloudcommon.entity.TccAccount;
import com.xiao.cloud.transactional.seatatccaccount.mapper.AccountMapper;
import com.xiao.cloud.transactional.seatatccaccount.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 22:40:56
 * @description
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, TccAccount> implements AccountService {
}
