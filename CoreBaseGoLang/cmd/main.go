package main

import (
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

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~{Start Run Project}~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	private := database.NewDatabase("SecureDB")
	public := database.NewDatabase("PublicDB")
	server := handler.NewCryptoHandler(private, public)
	server.RegisterRoutes()
	server.Run(":8080")
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

}
