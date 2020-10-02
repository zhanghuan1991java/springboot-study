package com.didispace.zookeeper.curator;

import cn.hutool.core.date.DateUtil;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zk")
public class ZKController {

    private static final Logger logger = LoggerFactory.getLogger(ZKController.class);

    @Autowired
    private CuratorFramework zkClient;

    private String lockPath = "/testl";

    @Autowired
    private ZookeeperLock zklock;

    private int k = 1;

    @GetMapping("/lock")
    public Boolean getLock() throws Exception {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    zklock.lock();
                    logger.info("Hello "+ DateUtil.now());
                    zklock.unlock();
                }
            }).start();
        }
        return true;
    }
}