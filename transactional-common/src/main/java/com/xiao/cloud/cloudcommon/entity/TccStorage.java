package com.xiao.cloud.cloudcommon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Data
@NoArgsConstructor
@ToString
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

    /**
     * 冻结库存
     */
    private Long frozenCount;

    private Date createTime;

}