package com.xiao.cloud.cloudcommon.config;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;



/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 21:16:08
 * @description
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String xid = RootContext.getXID();
        if (StringUtils.isNotEmpty(xid)) {
            requestTemplate.header(RootContext.KEY_XID, xid);
        }
    }


}
