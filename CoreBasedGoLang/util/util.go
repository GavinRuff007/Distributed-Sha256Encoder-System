package util

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/hex"
	"errors"
	"fmt"
	"io"
)

// generateAESKey creates a random 128-bit AES key
func GenerateAESKey() ([]byte, error) {
	key := make([]byte, 16) // 128-bit
	_, err := rand.Read(key)
	return key, err
}

// toHex converts a byte slice to a hex string
func ToHex(data []byte) string {
	return hex.EncodeToString(data)
}

// hexToBytes converts a hex string to a byte slice
func hexToBytes(hexStr string) ([]byte, error) {
	return hex.DecodeString(hexStr)
}

// aesEncrypt encrypts plaintext using AES-128-CBC and returns hex(iv) + hex(ciphertext)
func AesEncrypt(plaintext string, key []byte) (string, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	plaintextBytes := []byte(plaintext)

	// Padding (PKCS#7)
	padding := aes.BlockSize - len(plaintextBytes)%aes.BlockSize
	for i := 0; i < padding; i++ {
		plaintextBytes = append(plaintextBytes, byte(padding))
	}

	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	ciphertext := make([]byte, len(plaintextBytes))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, plaintextBytes)

	// concatenate hex(iv) + hex(cipher)
	return ToHex(iv) + ToHex(ciphertext), nil
}

// aesDecrypt decrypts hex(iv) + hex(ciphertext) using AES-128-CBC
func AesDecrypt(fullHex string, key []byte) (string, error) {
	if len(fullHex) < aes.BlockSize*2 {
		return "", errors.New("invalid ciphertext format")
	}

	ivHex := fullHex[:aes.BlockSize*2]
	cipherHex := fullHex[aes.BlockSize*2:]

	iv, err := hexToBytes(ivHex)
	if err != nil {
		return "", err
	}

	ciphertext, err := hexToBytes(cipherHex)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	if len(ciphertext)%aes.BlockSize != 0 {
		return "", fmt.Errorf("ciphertext is not a multiple of block size")
	}

	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(ciphertext, ciphertext)

	// Remove padding
	padding := int(ciphertext[len(ciphertext)-1])
	if padding <= 0 || padding > aes.BlockSize {
		return "", errors.New("invalid padding")
	}

	return string(ciphertext[:len(ciphertext)-padding]), nil
}
