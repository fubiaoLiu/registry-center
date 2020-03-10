package com.xiaoliu.common;

/**
 * @description: 服务实例信息
 * @author: FuBiaoLiu
 * @date: 2020/3/10
 */
public class ServerInstance {
    private static final long MAX_HEARTBEAT_INTERVAL = 90 * 1000L;
    private String instanceId;
    private String serverName;
    private String ip;
    private int port;
    private Lease lease;

    public ServerInstance(String instanceId, String serverName, String ip, int port) {
        this.instanceId = instanceId;
        this.serverName = serverName;
        this.ip = ip;
        this.port = port;
        this.lease = new Lease();
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public boolean isAlive() {
        return System.currentTimeMillis() - lease.lastRenewalTimestamp > MAX_HEARTBEAT_INTERVAL;
    }

    public void renew() {
        this.lease.renew();
    }

    class Lease {
        private long registrationTimestamp = System.currentTimeMillis();
        private long lastRenewalTimestamp = System.currentTimeMillis();

        public void renew() {
            this.lastRenewalTimestamp = System.currentTimeMillis();
        }
    }
}
