package com.xiao.cloud.cloudcommon.seata_at.stock.entity;

import java.util.Date;

/**
 * @projectName spring-cloud-learn-02
 * @author aloneMan
 * @createTime 2022-10-29 20:45:32
 * @description 
 */
public class PhoneStock {
    private Long id;

    private String name;

    private Integer count;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}