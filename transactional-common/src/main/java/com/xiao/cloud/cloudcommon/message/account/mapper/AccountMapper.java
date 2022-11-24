package com.xiao.cloud.cloudcommon.message.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.message.account.entity.TbAccount;
import com.xiao.cloud.cloudcommon.message.account.dto.AccountDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:02:17
 * @description 最大努力通知
 */
@Mapper
public interface AccountMapper extends BaseMapper<TbAccount> {

    /**
     * 增加或扣减余额
     *
     * @param accountDto
     *         扣减信息
     * @return 更新数量
     */
    @Update("UPDATE message_account.tb_account SET balance=balance+#{amount} " +
            "WHERE username=#{username,jdbcType=VARCHAR} AND balance >= #{amount}")
    int modifyBalance(AccountDto accountDto);

    /**
     * 增加事务日志
     * @param accountDto 日志信息
     * @return 添加数量
     */
    @Insert("INSERT INTO message_account.tb_tx(txId, username, amount) " +
            "VALUES (#{transactionalId,jdbcType=VARCHAR},#{username,jdbcType=VARCHAR},#{amount,jdbcType=DECIMAL})")
    int addTransactional(AccountDto accountDto);

    /**
     * 是否存在事务
     *
     * @param txId
     *         事务id
     * @return 返回存在数量
     */
    @Select("SELECT count(0) FROM message_account.tb_tx WHERE txId=#{txId,jdbcType=VARCHAR}")
    int isExist(String txId);
}
