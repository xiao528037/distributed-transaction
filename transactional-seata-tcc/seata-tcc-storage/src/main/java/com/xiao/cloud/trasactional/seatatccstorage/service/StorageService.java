package com.xiao.cloud.trasactional.seatatccstorage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.seata_tcc.storage.entity.TccStorage;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 23:39:38
 * @description
 */
@LocalTCC
public interface StorageService extends IService<TccStorage> {

    /**
     * 减少库存
     *
     * @param storageId
     *         库存ID
     * @param decreaseCount
     *         减少数量
     * @return
     */
    @TwoPhaseBusinessAction(name = "decreaseStorage", commitMethod = "storageCommit", rollbackMethod = "storageRollback")
    public TccStorage decreaseStorage(
            @BusinessActionContextParameter(paramName = "storageId") Long storageId,
            @BusinessActionContextParameter(paramName = "decreaseCount") Long decreaseCount);

    /**
     * 库存扣减提交
     * @param context
     * @return
     */
    public boolean storageCommit(BusinessActionContext context);

    /**
     * 库存扣减回滚
     * @param context
     * @return
     */
    public boolean storageRollback(BusinessActionContext context);

}
