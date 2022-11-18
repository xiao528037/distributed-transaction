package com.xiao.cloud.hmilytccaccount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:50:31
 * @description
 */
public interface AccountService extends IService<HmilyTccAccount> {

    /**
     * 余额扣减
     *
     * @param accountId
     * @param mount
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "commit", cancelMethod = "rollback")
    public HmilyTccAccount deductionBalance(Long accountId, Long mount);

    /**
     * 余额扣减提交
     *
     * @param accountId
     * @param mount
     * @return
     */
    public HmilyTccAccount commit(Long accountId, Long mount);

    /**
     * 余额扣减回滚
     *
     * @param accountId
     * @param mount
     * @return
     */
    public HmilyTccAccount rollback(Long accountId, Long mount);
}
