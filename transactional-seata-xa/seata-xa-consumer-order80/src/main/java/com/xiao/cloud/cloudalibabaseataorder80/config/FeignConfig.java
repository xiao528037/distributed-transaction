package com.xiao.cloud.cloudalibabaseataorder80.config;

import com.alibaba.cloud.commons.lang.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 21:16:08
 * @description
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String xid = RootContext.getXID();
        if (StringUtils.isNotEmpty(xid)) {
            requestTemplate.header(RootContext.KEY_XID, xid);
        }
    }
}
