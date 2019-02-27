package com.wukong.zookeeperlock.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenfucheng
 * zk配置
 */
@Configuration
public class ZookeeperConfig {

    /**
     * 创建zookeeper的客户端
     * @return
     */
    @Bean
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        return client;
    }

    /**
     * //创建分布式锁, 锁空间的根节点路径为/curator/lock
     * @param curatorFramework
     * @return
     */
    @Bean
    public InterProcessMutex interProcessMutex(CuratorFramework curatorFramework){
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/curator/lock");
        return interProcessMutex;
    }
}
