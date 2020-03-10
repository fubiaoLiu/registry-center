package com.xiaoliu.server;

import com.xiaoliu.common.ServerInstance;

/**
 * @description: API
 * @author: FuBiaoLiu
 * @date: 2020/3/10
 */
public class RegistryController {
    private InstanceRegistry instanceRegistry;

    public RegistryController(InstanceRegistry instanceRegistry) {
        this.instanceRegistry = instanceRegistry;
    }

    /**
     * 注册
     *
     * @param serverInstance
     */
    public void register(ServerInstance serverInstance) {
        instanceRegistry.register(serverInstance);
    }

    /**
     * 续约
     *
     * @param serverInstance
     */
    public void renew(ServerInstance serverInstance) {
        instanceRegistry.renew(serverInstance);
    }
}
