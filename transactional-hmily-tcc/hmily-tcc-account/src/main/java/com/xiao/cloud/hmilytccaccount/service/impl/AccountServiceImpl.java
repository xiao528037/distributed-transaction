package com.xiao.cloud.hmilytccaccount.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountNestedDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.mapper.AccountMapper;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.dto.InventoryDTO;
import com.xiao.cloud.hmilytccaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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
    public HmilyTccAccount payment(AccountDTO accountDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行支付接口try方法", HmilyContextHolder.get().getTransId());
        //扣减金额
        HmilyTccAccount hmilyTccAccount = decrease(accountDTO);
        return hmilyTccAccount;
    }


    @Override
    public HmilyTccAccount paymentWithTryException(AccountDTO accountDTO) {
        throw new RuntimeException(accountDTO.getUserId() + ">> 用户,扣减" + accountDTO.getAmount().doubleValue() + "失败");
    }

    @Override
    public HmilyTccAccount paymentWithTryTimeOut(AccountDTO accountDTO) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //扣减金额
        HmilyTccAccount hmilyTccAccount = decrease(accountDTO);
        return hmilyTccAccount;
    }

    @Override
    public HmilyTccAccount paymentWithNested(AccountNestedDTO accountDTO) {
        return null;
    }

    @Override
    public HmilyTccAccount paymentWithNestedException(AccountNestedDTO nestedDTO) {
        return null;
    }

    @Override
    public HmilyTccAccount commit(AccountDTO accountDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {} <<<<<<<<<<<< ", "执行支付接口commit方法", HmilyContextHolder.get().getTransId());
        accountMapper.commit(accountDTO);
        QueryWrapper<HmilyTccAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HmilyTccAccount::getUserId, accountDTO.getUserId());
        HmilyTccAccount hmilyTccAccount = accountMapper.selectOne(queryWrapper);
        return hmilyTccAccount;
    }

    @Override
    public HmilyTccAccount rollback(AccountDTO accountDTO) {
        log.info(">>>>>>>>>>> {} 全局事务ID {}  <<<<<<<<<<<< ", "执行支付接口rollback方法", HmilyContextHolder.get().getTransId());
        accountMapper.rollback(accountDTO);
        QueryWrapper<HmilyTccAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HmilyTccAccount::getUserId, accountDTO.getUserId());
        HmilyTccAccount hmilyTccAccount = accountMapper.selectOne(queryWrapper);
        return hmilyTccAccount;
    }

    @Override
    public HmilyTccAccount commitNested(AccountNestedDTO accountNestedDTO) {
        return null;
    }

    @Override
    public HmilyTccAccount rollbackNested(AccountNestedDTO accountNestedDTO) {
        return null;
    }


    /**
     * 冻结金额
     *
     * @param accountDTO
     */
    private HmilyTccAccount decrease(AccountDTO accountDTO) {
        int decrease = accountMapper.update(accountDTO);
        if (decrease <= 0) {
            throw new RuntimeException(accountDTO.getUserId() + ">> 用户,扣减" + accountDTO.getAmount().doubleValue() + "失败");
        }
        QueryWrapper<HmilyTccAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HmilyTccAccount::getUserId, accountDTO.getUserId());
        HmilyTccAccount hmilyTccAccount = accountMapper.selectOne(queryWrapper);
        return hmilyTccAccount;
    }

    private AccountDTO buildAccountDTO(AccountNestedDTO nestedDTO) {
        AccountDTO dto = new AccountDTO();
        dto.setAmount(nestedDTO.getAmount());
        dto.setUserId(nestedDTO.getUserId());
        return dto;
    }

    private InventoryDTO buildInventoryDTO(AccountNestedDTO nestedDTO) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(nestedDTO.getCount());
        inventoryDTO.setProductId(nestedDTO.getProductId());
        return inventoryDTO;
    }
}
