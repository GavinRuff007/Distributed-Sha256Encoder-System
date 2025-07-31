package privatekey

import (
	"CoreBasedGoLang/config"
	"context"
	"google.golang.org/grpc"
	"log"
	"os"
	"time"
)

func SendPrivateKeyToNode(nodeAddr, privateKey string) {
	path := os.Getenv("CONFIG_PATH")
	if path == "" {
		log.Fatal("CONFIG_PATH env variable is not set")
	}
	cfg, err := config.LoadConfig(path)
	if err != nil {
		log.Fatalf("failed to load config: %v", err)
	}

	nodeToAddr := cfg.Nodes
	addr, ok := nodeToAddr[nodeAddr]
	if !ok {
		log.Printf("Unknown node name: %s", nodeAddr)
		return
	}
	log.Println("Sending private key to node", nodeAddr, "at address", addr)
	conn, err := grpc.Dial(addr, grpc.WithInsecure(), grpc.WithBlock(), grpc.WithTimeout(5*time.Second))
	if err != nil {
		log.Printf("Failed to connect to node %s: %v", nodeAddr, err)
		return
	}
	defer conn.Close()

	client := NewPrivateKeyServiceClient(conn)
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	resp, err := client.ReceivePrivateKey(ctx, &PrivateKeyMessage{PrivateKey: privateKey})
	if err != nil {
		log.Printf("Failed to send private key to node %s: %v", nodeAddr, err)
		return
	}

	if resp.Success {
		log.Printf("Successfully sent private key to node %s", nodeAddr)
	} else {
		log.Printf("Node %s did not accept the private key", nodeAddr)
	}
}
