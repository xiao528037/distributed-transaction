package com.xiao.cloud.cloudcommon.message.recharge.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.message.account.dto.AccountDto;
import com.xiao.cloud.cloudcommon.message.recharge.dto.RechargeDto;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbRecharge;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:49:21
 * @description
 */
@Mapper
public interface RechargeMapper extends BaseMapper<TbRecharge> {

    /**
     * 增加积分
     *
     * @param dto
     * @return 返回更新数量
     */
    @Update("UPDATE message_recharge.tb_recharge SET integral = integral+#{integral,jdbcType=DECIMAL} WHERE" +
            "username = #{username,jdbcType=VARCHAR}")
    int addIntegral(RechargeDto dto);


    /**
     * 增加事务日志
     *
     * @param rechargeDto
     *         日志信息
     * @return 添加数量
     */
    @Insert("INSERT INTO message_recharge.tb_tx(id, txId, username,is_success ) " +
            "VALUES (#{transactionalId,jdbcType=VARCHAR},#{username,jdbcType=VARCHAR},true)")
    int addTransactional(RechargeDto rechargeDto);

    /**
     * 获取事务信息
     * @param txId 事务ID
     * @return 事务信息
     */
    @Select("SELECT * FROM message_recharge.tb_tx WHERE txId=#{txId,jdbcType=VARCHAR}")
    TbTx getTx(String txId);

    /**
     * 判断事务ID是否存在
     *
     * @param txId
     *         事务ID
     * @return 更新数量
     */
    @Select("SELECT count(0) FROM message_recharge.tb_tx WHERE txId=#{txId,jdbcType=VARCHAR}")
    int isExist(String txId);
}
