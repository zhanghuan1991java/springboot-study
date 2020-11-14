package com.didispace.web;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("user","jackZhanG");
        MDC.put("operId", IdUtil.fastSimpleUUID());

        log.info("进入了MyFilter ......... doFilter........");
        chain.doFilter(request,response);
        log.info("离开了MyFilter ......... doFilter........");

        MDC.put("end","true");
    }

    @Override
    public void init(FilterConfig filterConfig){
        log.info("MyFilter init...");
    }

    @Override
    public void destroy() {
        log.info("MyFilter destroy...");
    }

}
