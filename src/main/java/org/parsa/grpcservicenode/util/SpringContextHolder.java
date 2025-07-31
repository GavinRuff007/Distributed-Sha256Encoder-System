package org.parsa.grpcservicenode.util;

import org.parsa.grpcservicenode.service.NodeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ConfigurableApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        if (ctx instanceof ConfigurableApplicationContext) {
            context = (ConfigurableApplicationContext) ctx;
        }
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void restartApp() {
        if (context == null) {
            System.err.println("❌ SpringContext is null, cannot restart.");
            return;
        }

        try {
            NodeService nodeService = context.getBean(NodeService.class);
            nodeService.shutdownGrpcServer();

            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(200);
                    context.close();
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(false);
            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
