package com.xiao.cloud.hmilytccorder.openapi;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountNestedDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:55:06
 * @description
 */
@FeignClient(name = "hmily-tcc-account-service", path = "/hmily/account")
public interface AccountApi {

    /**
     * 扣减余额-正常
     *
     * @param accountDTO
     *         扣减信息
     * @return 扣减信息
     */

    @Hmily
    @PostMapping("/payment")
    CommonResult<HmilyTccAccount> payment(@RequestBody AccountDTO accountDTO);

    /**
     * 扣减余额-异常
     *
     * @param accountDTO
     *         扣减信息
     * @return 扣减信息
     */
    @PostMapping("/paymentException")
    CommonResult<HmilyTccAccount> paymentException(@RequestBody AccountDTO accountDTO);

    /**
     * 扣减余额-超时
     *
     * @param accountDTO
     *         扣减信息
     * @return 扣减信息
     */
    @PostMapping("/paymentTime")
    public CommonResult<HmilyTccAccount> paymentTimeout(@RequestBody AccountDTO accountDTO);

    /**
     * 扣减余额-嵌套调用-正常
     *
     * @param accountNestedDTO
     *         扣减金额和金额信息
     * @return 扣减信息
     */
    @PostMapping("/paymentNested")
    CommonResult<HmilyTccAccount> paymentWithNested(@RequestBody AccountNestedDTO accountNestedDTO);

    /**
     * 扣减余额-嵌套调用-异常
     *
     * @param accountNestedDTO
     *         扣减金额和金额信息
     * @return 扣减信息
     */
    @PostMapping("/paymentNestedException")
    CommonResult<HmilyTccAccount> paymentNestedException(@RequestBody AccountNestedDTO accountNestedDTO);

    /**
     * 用户信息
     *
     * @param userId
     *         用户ID
     * @return 用户信息
     */
    @GetMapping("/get/{userId}")
    CommonResult<HmilyTccAccount> getAccount(@PathVariable("userId") String userId);
}
