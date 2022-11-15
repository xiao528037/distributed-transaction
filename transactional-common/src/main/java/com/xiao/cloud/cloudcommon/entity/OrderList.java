package com.xiao.cloud.cloudcommon.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName spring-cloud-learn-02
 * @author aloneMan
 * @createTime 2022-10-30 14:05:15
 * @description 
 */
/**
    * 订单信息
    */
@Data
public class OrderList {
    private Long id;
    /**
    * 商品数量
    */
    private Integer commodityCount;
    /**
    * 商品总价
    */
    private BigDecimal totalPricel;
    /**
    * 商品ID
    */
    private Long commodityId;

    private Date createTime;
}