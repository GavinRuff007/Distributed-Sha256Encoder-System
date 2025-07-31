package consensus

import (
	"CoreBasedGoLang/broadcast"
	"CoreBasedGoLang/database"
	privatekey "CoreBasedGoLang/privateKey"
	"context"
	"fmt"
	"google.golang.org/grpc"
	"log"
	"net"
	"sync"
	"time"
)

type CoreConsensusServer struct {
	UnimplementedCoreConsensusServiceServer
	mu          sync.Mutex
	LastLeader  string
	LeaderCount int
	DB          *database.Database
	BcastServer *broadcast.Server
	LastHash    string
	Email       string
	// رای‌های هر candidate (کلید: اسم نود)
	VoteCounts map[string]int
	// برای جلوگیری از رای تکراری هر voter (map[voter_term]bool)
	AlreadyVoted map[string]bool
}

func (s *CoreConsensusServer) ReportLeader(ctx context.Context, req *LeaderMessage) (*Ack, error) {
	s.mu.Lock()
	s.LastLeader = req.LeaderName
	s.LeaderCount++
	countNow := s.LeaderCount
	log.Printf("Leader reported: %s", req.LeaderName)
	log.Printf("Count leader: %d", countNow)
	s.mu.Unlock()

	s.checkLeaderConditionAndSendKey()
	return &Ack{Success: true}, nil
}

// متد جدید: ثبت و شمارش رای‌ها برای هر candidate
func (s *CoreConsensusServer) ReportVote(ctx context.Context, req *VoteReport) (*Ack, error) {
	s.mu.Lock()
	defer s.mu.Unlock()

	if s.VoteCounts == nil {
		s.VoteCounts = make(map[string]int)
	}
	if s.AlreadyVoted == nil {
		s.AlreadyVoted = make(map[string]bool)
	}

	key := fmt.Sprintf("%s_%d", req.Voter, req.Term)

	s.AlreadyVoted[key] = true

	s.VoteCounts[req.Candidate]++
	count := s.VoteCounts[req.Candidate]
	log.Printf("Vote received: %s voted for %s (term: %d). Total votes for %s: %d", req.Voter, req.Candidate, req.Term, req.Candidate, count)

	majority := 3
	if count >= majority {
		log.Printf("%s has MAJORITY VOTES! (Total: %d)", req.Candidate, count)
		s.LastLeader = req.Candidate

		s.LeaderCount = count
		go s.checkLeaderConditionAndSendKey()

	}
	return &Ack{Success: true}, nil
}

func (s *CoreConsensusServer) checkLeaderConditionAndSendKey() {
	s.mu.Lock()
	defer s.mu.Unlock()

	percent := (float64(s.LeaderCount) / 5.0) * 100
	log.Println("LeaderCount/5*100 =", percent)

	if percent >= 50 {
		log.Println("Condition passed: LeaderCount/5 >= 50. Sending private key to leader.")
		key, err := s.DB.GetLastPrivateKey("PrivateKey")
		if err != nil {
			log.Printf("Failed to get last private key: %v", err)
		} else {
			go privatekey.SendPrivateKeyToNode(s.LastLeader, key)
		}
		s.LeaderCount = 0
		// پاک کردن map رأی‌ها برای round بعدی
		if s.VoteCounts != nil {
			for k := range s.VoteCounts {
				delete(s.VoteCounts, k)
			}
		}
		if s.AlreadyVoted != nil {
			for k := range s.AlreadyVoted {
				delete(s.AlreadyVoted, k)
			}
		}
	} else {
		log.Println("Condition not met, broadcasting again after 2s...")
		go func(hash string, email string) {
			time.Sleep(2 * time.Second)
			if s.BcastServer != nil && hash != "" {
				s.BcastServer.Broadcast(hash, email)
			}
		}(s.LastHash, s.Email)
	}
}

func StartConsensusServer(port string) {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("ConsensusServer failed to listen: %v", err)
	}
	grpcServer := grpc.NewServer()
	srv := &CoreConsensusServer{
		DB: database.NewDatabase("SecureDB"),
	}
	RegisterCoreConsensusServiceServer(grpcServer, srv)
	log.Printf("gRPC Consensus server running on %s", port)
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("ConsensusServer failed to serve: %v", err)
	}
}
