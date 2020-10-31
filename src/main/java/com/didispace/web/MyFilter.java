package com.didispace.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("进入了MyFilter ......... doFilter........");
        chain.doFilter(request,response);
        log.info("离开了MyFilter ......... doFilter........");


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
