package main

import (
	"CoreBasedGoLang/broadcast"
	"CoreBasedGoLang/consensus"
	"CoreBasedGoLang/database"
	"CoreBasedGoLang/handler"
	"fmt"
	"time"
)

const (
	RED   = "\033[1;31m"
	GREEN = "\033[1;32m"
	RESET = "\033[0m"
)

func main() {
	// --- بنر ---
	banner := RED + `
  _____                       ______  __ 
 |  __ \                     |  ____|/ _|
 | |__) |_ _ _ __ ___  __ _  | |__  | |_ 
 |  ___/ _` + "`" + ` | '__/ __|/ _` + "`" + ` | |  __| |  _|
 | |  | (_| | |  \__ \ (_| | | |____| |  
 |_|   \__,_|_|  |___/\__,_| |______|_|` + RESET

	for _, c := range banner {
		fmt.Printf("%c", c)
		time.Sleep(2 * time.Millisecond)
	}
	fmt.Println(GREEN + "\nDistributed Secure Core is Starting..." + RESET)

	privateDB := database.NewDatabase("SecureDB")
	publicDB := database.NewDatabase("PublicDB")

	bcastServer := &broadcast.Server{}
	go bcastServer.Start(":50051")

	go consensus.StartConsensusServer(":6000")

	apiServer := handler.NewCryptoHandler(privateDB, publicDB, bcastServer)
	apiServer.RegisterRoutes()
	apiServer.Run(":8080")
}
