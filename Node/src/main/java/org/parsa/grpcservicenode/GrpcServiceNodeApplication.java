package org.parsa.grpcservicenode;

import org.parsa.grpcservicenode.privateKey.PrivateKeyReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GrpcServiceNodeApplication implements ApplicationRunner {

    @Autowired
    private PrivateKeyReceiverService privateKeyReceiverService;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) {
        // ست کردن context برای ری‌استارت
        privateKeyReceiverService.setApplicationContext(context);
    }

    public static void main(String[] args) {
        SpringApplication.run(GrpcServiceNodeApplication.class, args);
    }
}

