package com.xiao.cloud.cloudcommon.message.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:06:08
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto {
    private String username;
    private BigDecimal amount;
    private String TransactionalId;
}
