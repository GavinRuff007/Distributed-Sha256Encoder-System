package main

import (
	"CoreBasedGoLang/broadcast"
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
	// بنر زیبایی که نمایش داده می‌شود
	banner := RED + `
  _____                       ______  __ 
 |  __ \                     |  ____|/ _|
 | |__) |_ _ _ __ ___  __ _  | |__  | |_ 
 |  ___/ _` + "`" + ` | '__/ __|/ _` + "`" + ` | |  __| |  _|
 | |  | (_| | |  \__ \ (_| | | |____| |  
 |_|   \__,_|_|  |___/\__,_| |______|_|` + RESET

	for _, c := range banner {
		fmt.Printf("%c", c)
		time.Sleep(5 * time.Millisecond)
	}
	fmt.Println(GREEN + "\n Secure API is Starting..." + RESET)

	// دیتابیس‌های خصوصی و عمومی
	privateDB := database.NewDatabase("SecureDB")
	publicDB := database.NewDatabase("PublicDB")

	// راه‌اندازی gRPC Broadcast Server (برای Nodeها)
	bcastServer := &broadcast.Server{}
	go bcastServer.Start(":50051")

	// راه‌اندازی REST API (با دسترسی به bcastServer برای ارسال Broadcast)
	apiServer := handler.NewCryptoHandler(privateDB, publicDB, bcastServer)
	apiServer.RegisterRoutes()
	apiServer.Run(":8080")
}
