package broadcast

import (
	"log"
	"net"
	"sync"
	"time"

	pb "CoreBasedGoLang/proto" // مسیر خروجی protoc

	"google.golang.org/grpc"
)

type Server struct {
	pb.UnimplementedBroadcastServiceServer
	mu          sync.Mutex
	subscribers []chan *pb.BroadcastMessage
}

// Subscribe: Nodeها به این متد وصل می‌شوند
func (s *Server) Subscribe(req *pb.SubscribeRequest, stream pb.BroadcastService_SubscribeServer) error {
	ch := make(chan *pb.BroadcastMessage, 10)

	s.mu.Lock()
	s.subscribers = append(s.subscribers, ch)
	s.mu.Unlock()

	log.Printf("Node %s subscribed", req.NodeId)

	for {
		select {
		case msg := <-ch:
			if err := stream.Send(msg); err != nil {
				log.Printf("Error sending to %s: %v", req.NodeId, err)
				return err
			}
		}
	}
}

// Broadcast: به همه Nodeها پیام بفرست
func (s *Server) Broadcast(hash string) {
	s.mu.Lock()
	defer s.mu.Unlock()

	msg := &pb.BroadcastMessage{
		Hash:      hash,
		Timestamp: time.Now().Format(time.RFC3339),
	}
	log.Printf("Broadcasting hash: %s", hash)
	for _, sub := range s.subscribers {
		sub <- msg
	}
}

// Start: سرور gRPC را راه‌اندازی می‌کند
func (s *Server) Start(port string) {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	grpcServer := grpc.NewServer()
	pb.RegisterBroadcastServiceServer(grpcServer, s)
	log.Printf("gRPC Broadcast server running on %s", port)
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
