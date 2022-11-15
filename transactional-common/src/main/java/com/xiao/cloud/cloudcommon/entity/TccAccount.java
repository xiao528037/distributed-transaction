package com.xiao.cloud.cloudcommon.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-15 22:56:44
 * @description 
 */
/**
    * 用户相关表
    */
public class TccAccount {
    private Long id;

    /**
    * 用户名称
    */
    private String username;

    private BigDecimal userOver;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getUserOver() {
        return userOver;
    }

    public void setUserOver(BigDecimal userOver) {
        this.userOver = userOver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}