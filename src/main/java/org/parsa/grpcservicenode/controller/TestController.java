package org.parsa.grpcservicenode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class TestController {
//    private final GrpcBClient grpcBClient;
//
//    public TestController(GrpcBClient grpcBClient) {
//        this.grpcBClient = grpcBClient;
//    }
//
//    @GetMapping("/call-b/{name}")
//    public String callB(@PathVariable String name) {
//        return grpcBClient.sendHello(name);
//    }
//}
