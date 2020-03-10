package com.xiaoliu.server;

import com.xiaoliu.common.ServerInstance;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegistryServerBootstrapTest {
    @Test
    public void testStartRegistryServer() {
        RegistryServerBootstrap bootstrap = RegistryServerBootstrap.getInstance();
        bootstrap.start();

        String instanceId = UUID.randomUUID().toString().replace("-", "");
        ServerInstance serverInstance = new ServerInstance(instanceId, "TEST", "192.168.0.1", 8080);
        RegistryController controller = new RegistryController(bootstrap.getRegistry());
        controller.register(serverInstance);
        controller.renew(serverInstance);

        try {
            Thread.sleep(35000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bootstrap.shutdown();
    }
}
