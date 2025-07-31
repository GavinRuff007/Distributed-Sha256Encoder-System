package broadcast

import (
	broadcast "CoreBasedGoLang/proto"
	"google.golang.org/grpc"
	"log"
	"net"
	"sync"
)

type Server struct {
	broadcast.UnimplementedBroadcastServiceServer
	mu          sync.Mutex
	subscribers []chan *broadcast.BroadcastMessage
}

func (s *Server) Subscribe(req *broadcast.SubscribeRequest, stream broadcast.BroadcastService_SubscribeServer) error {
	ch := make(chan *broadcast.BroadcastMessage, 10)

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

func (s *Server) Broadcast(hash string, email string) {
	s.mu.Lock()
	defer s.mu.Unlock()

	msg := &broadcast.BroadcastMessage{
		Hash:  hash,
		Email: email,
	}
	log.Printf("Broadcasting hash: %s", hash)
	for _, sub := range s.subscribers {
		sub <- msg
	}
}

func (s *Server) Start(port string) {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	grpcServer := grpc.NewServer()
	broadcast.RegisterBroadcastServiceServer(grpcServer, s)
	log.Printf("gRPC Broadcast server running on %s", port)
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
