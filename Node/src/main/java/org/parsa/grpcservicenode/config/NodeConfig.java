package org.parsa.grpcservicenode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NodeConfig {

    @Value("${app.node-name}")
    private String myNodeName;

    @Value("#{'${app.peers}'.split(',')}")
    private List<String> peers;

    public String getMyNodeName() {
        return myNodeName;
    }

    public List<String> getPeers() {
        return peers;
    }
}