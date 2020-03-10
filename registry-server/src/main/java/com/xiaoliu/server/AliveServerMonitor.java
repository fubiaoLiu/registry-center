package com.xiaoliu.server;

import com.xiaoliu.common.ServerInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 存活服务监控线程
 * @author: FuBiaoLiu
 * @date: 2020/3/10
 */
public class AliveServerMonitor extends Thread {
    private static final long EXPIRED_SCAN_INTERVAL = 30 * 1000L;
    private RegistryServerBootstrap bootstrap;
    private InstanceRegistry registryHolder;

    public AliveServerMonitor(RegistryServerBootstrap bootstrap, InstanceRegistry registryHolder) {
        this.bootstrap = bootstrap;
        this.registryHolder = registryHolder;
    }

    @Override
    public void run() {
        HashMap<String, Map<String, ServerInstance>> registry;

        while (bootstrap.isRunning()) {
            System.out.println("Scan And Expire Server...");
            registry = registryHolder.getRegistry();
            for (Map<String, ServerInstance> serverInstanceMap : registry.values()) {
                for (Map.Entry<String, ServerInstance> entry : serverInstanceMap.entrySet()) {
                    if (entry.getValue().isAlive()) {
                        continue;
                    }
                    serverInstanceMap.remove(entry.getKey());
                }
            }
            try {
                Thread.sleep(EXPIRED_SCAN_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
