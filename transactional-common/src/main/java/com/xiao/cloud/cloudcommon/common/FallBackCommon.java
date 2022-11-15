package com.xiao.cloud.cloudcommon.common;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-17 23:15:16
 * @description
 */
public class FallBackCommon {
    private AtomicInteger countException = new AtomicInteger(0);

    public CommonResult paymentInfoTimeoutHandler() {
        return new CommonResult(0x10001L, "Hystrix 超时测试成功", countException.getAndIncrement());
    }
}
