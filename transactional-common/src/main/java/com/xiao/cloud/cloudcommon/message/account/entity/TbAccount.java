package com.xiao.cloud.cloudcommon.message.account.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:00:55
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbAccount {
    private Long id;

    private String username;

    private BigDecimal balance;

    private Date createTime;
}