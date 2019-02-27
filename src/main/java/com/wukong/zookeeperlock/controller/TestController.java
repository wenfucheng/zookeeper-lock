package com.wukong.zookeeperlock.controller;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private InterProcessMutex interProcessMutex;


    @RequestMapping("/test")
    public String testZkLock() throws Exception {
        interProcessMutex.acquire();
        Thread.sleep(10000);
        long currentTimeMillis = System.currentTimeMillis();
        System.err.println(currentTimeMillis);
        interProcessMutex.release();
        return String.valueOf(currentTimeMillis);

    }
}
