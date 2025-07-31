package handler

import (
	"CoreBasedGoLang/broadcast"
	"CoreBasedGoLang/database"
	"CoreBasedGoLang/util"
	"encoding/hex"
	"github.com/gin-gonic/gin"
	"net/http"
	"strconv"
	"strings"
)

type CryptoStruct struct {
	router   *gin.Engine
	database *database.Database
	pub      *database.Database
	bcast    *broadcast.Server
	email    string
}

type RequestData struct {
	Email     string `json:"email" binding:"required,email"`
	PlainText string `json:"plainText" binding:"required"`
}

func NewCryptoHandler(database *database.Database, pub *database.Database, bcast *broadcast.Server) *CryptoStruct {
	return &CryptoStruct{
		router:   gin.Default(),
		database: database,
		pub:      pub,
		bcast:    bcast,
	}
}

func (cs *CryptoStruct) RegisterRoutes() {
	cs.router.POST("/encrypt", cs.encryptHandler)
	cs.router.POST("/decrypt", cs.decryptHandler)
}

func (cs *CryptoStruct) Run(addr string) {
	cs.router.Run(addr)
}

func (cs *CryptoStruct) encryptHandler(c *gin.Context) {
	//data, _ := c.GetRawData()
	//

	var requestData RequestData

	if err := c.BindJSON(&requestData); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"status": "error", "message": "Invalid JSON"})
		return
	}

	email := requestData.Email
	plaintext := requestData.PlainText

	key, err := util.GenerateAESKey()
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"status": "error", "message": "Key generation failed"})
		return
	}

	keyHex := util.ToHex(key)
	id, err := cs.database.StoreKey(keyHex, "PrivateKey")
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"status": "error", "message": "Failed to store key"})
		return
	}

	encrypted, err := util.AesEncrypt(plaintext, key)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"status": "error", "message": "Encryption failed"})
		return
	}

	_, err = cs.pub.StoreKeyWithID(encrypted, id, "PublicKey")
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"status": "error", "message": "Failed to store encrypted text"})
		return
	}

	if cs.bcast != nil {
		cs.bcast.Broadcast(encrypted, email)
	}

	c.JSON(http.StatusOK, gin.H{
		"status":         "success",
		"key_id":         id,
		"encrypted_text": encrypted,
	})
}

func (cs *CryptoStruct) decryptHandler(c *gin.Context) {
	idStr := c.Query("id")
	id, _ := strconv.Atoi(idStr)

	encrypted, err := cs.pub.GetKeyById(id, "PublicKey")
	if err != nil || strings.TrimSpace(encrypted) == "" {
		c.JSON(http.StatusBadRequest, gin.H{"status": "error", "message": "Encrypted text not found"})
		return
	}

	keyHex, err := cs.database.GetKeyById(id, "PrivateKey")
	if err != nil || strings.TrimSpace(keyHex) == "" {
		c.JSON(http.StatusBadRequest, gin.H{"status": "error", "message": "Key not found"})
		return
	}

	keyBytes, err := hex.DecodeString(keyHex)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"status": "error", "message": "Invalid key format"})
		return
	}

	decrypted, err := util.AesDecrypt(encrypted, keyBytes)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"status": "error", "message": "Decryption failed"})
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"status":         "success",
		"decrypted_text": decrypted,
	})
}
