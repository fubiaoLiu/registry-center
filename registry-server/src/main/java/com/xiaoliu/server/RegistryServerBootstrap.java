package com.xiaoliu.server;

/**
 * @description: 注册中心服务
 * @author: FuBiaoLiu
 * @date: 2020/3/10
 */
public class RegistryServerBootstrap {
    private static RegistryServerBootstrap instance = new RegistryServerBootstrap();
    private InstanceRegistry registry;
    private boolean isRunning;
    private AliveServerMonitor aliveServerMonitor;

    private RegistryServerBootstrap() {
        this.registry = new InstanceRegistry();
        this.aliveServerMonitor = new AliveServerMonitor(this, registry);
    }

    public static RegistryServerBootstrap getInstance() {
        return instance;
    }

    public void start() {
        this.isRunning = true;
        this.aliveServerMonitor.start();
        System.out.println("Registry Server Start.");
    }

    public void shutdown() {
        this.isRunning = false;
        this.aliveServerMonitor.interrupt();
        System.out.println("Registry Server Close.");
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public InstanceRegistry getRegistry() {
        return registry;
    }
}
