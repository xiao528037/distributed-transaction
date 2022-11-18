package com.xiao.cloud.cloudcommon.seata_tcc.order.entity;

import java.util.Date;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-15 23:10:20
 * @description 
 */
/**
    * 订单表
    */
public class TccOrder {
    private Long id;

    /**
    * 用户ID
    */
    private Long accountId;

    /**
    * 库存id
    */
    private Long storageId;

    /**
    * 订单创建时间
    */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}