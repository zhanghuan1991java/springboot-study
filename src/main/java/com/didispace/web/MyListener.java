package com.didispace.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class MyListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("MyListener>>>requestInitialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("MyListener>>>requestDestroyed");

        MDC.clear();
    }

}