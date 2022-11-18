package com.xiao.cloud.cloudcommon.seata_at.commodity.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName spring-cloud-learn-02
 * @author aloneMan
 * @createTime 2022-10-30 15:10:54
 * @description 
 */
public class CommodityDetails {
    private Long id;

    private Long stockId;

    private String commpany;

    private String cpu;

    /**
    * 商品价格
    */
    private BigDecimal price;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getCommpany() {
        return commpany;
    }

    public void setCommpany(String commpany) {
        this.commpany = commpany;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}