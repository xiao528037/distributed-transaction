package com.xiao.cloud.transactional.seatatccaccount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.seata_tcc.account.entity.TccAccount;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;


/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 22:40:35
 * @description
 */
@LocalTCC
public interface AccountService extends IService<TccAccount> {


    /**
     * 扣减余额
     *
     * @param accountId
     *         账号Id
     * @param deductionAmount
     *         扣除余额
     * @return 返回扣减后的数据
     */
    @TwoPhaseBusinessAction(name = "deductionBalance", commitMethod = "deductionCommit", rollbackMethod = "deductionRollback")
    TccAccount deductionAmount(@BusinessActionContextParameter(paramName = "accountId") Long accountId,
                                @BusinessActionContextParameter(paramName = "deductionAmount") BigDecimal deductionAmount);

    /**
     * 事务提交
     * @param context
     * @return
     */
    public boolean deductionCommit(BusinessActionContext context);

    /**
     * 回滚
     * @param context
     * @return
     */
    public boolean deductionRollback(BusinessActionContext context);

}
