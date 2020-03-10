package com.xiaoliu.server;

import com.xiaoliu.common.ServerInstance;

import java.util.HashMap;
import java.util.Map;

public class InstanceRegistry {
    private HashMap<String, Map<String, ServerInstance>> registry
            = new HashMap<>();

    public HashMap<String, Map<String, ServerInstance>> getRegistry() {
        return registry;
    }

    public void register(ServerInstance serverInstance) {
        Map<String, ServerInstance> serverInstanceMap = registry.computeIfAbsent(serverInstance.getServerName(), k -> new HashMap<>());
        serverInstanceMap.put(serverInstance.getInstanceId(), serverInstance);
        System.out.println(serverInstance.getServerName() + "服务" + serverInstance.getInstanceId() + "实例注册");
    }

    public void renew(ServerInstance serverInstance) {
        Map<String, ServerInstance> serverInstanceMap = registry.computeIfAbsent(serverInstance.getServerName(), k -> new HashMap<>());
        serverInstanceMap.get(serverInstance.getInstanceId()).renew();
        System.out.println(serverInstance.getServerName() + "服务" + serverInstance.getInstanceId() + "实例续约");
    }
}
