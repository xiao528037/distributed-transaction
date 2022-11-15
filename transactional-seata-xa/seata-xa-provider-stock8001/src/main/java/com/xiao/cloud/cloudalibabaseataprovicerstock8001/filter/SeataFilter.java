package com.xiao.cloud.cloudalibabaseataprovicerstock8001.filter;


import com.alibaba.cloud.commons.lang.StringUtils;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-15 18:25:39
 * @description
 */
@WebFilter(filterName = "seataFilter", urlPatterns = "/*")
@Component
@Slf4j
public class SeataFilter implements Filter {

    @PostConstruct
    public  void init(){
        log.info("{} ","hello work");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;
        //手动绑定XID
        String xid = rq.getHeader("XID");
        if (StringUtils.isNotBlank(xid)) {
            RootContext.bind(xid);
        }
        chain.doFilter(request, response);
    }
}
