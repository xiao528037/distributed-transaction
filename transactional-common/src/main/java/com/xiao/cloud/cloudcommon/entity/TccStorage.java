package com.xiao.cloud.cloudcommon.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-15 23:34:02
 * @description 
 */
/**
    * 库存表
    */
public class TccStorage {
    private Long id;

    /**
    * 库存数量
    */
    private Long storageCount;

    /**
    * 商品价格
    */
    private BigDecimal commodityPrice;

    /**
    * 商品名称
    */
    private String commodityName;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(Long storageCount) {
        this.storageCount = storageCount;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}