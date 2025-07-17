#include "util.h"
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <sstream>
#include <iomanip>
#include <vector>
#include <iostream>
#include <openssl/aes.h>

void Util::generate_aes_key(unsigned char* key) {
    RAND_bytes(key, AES_BLOCK_SIZE);
}

std::string Util::to_hex(const unsigned char* data, size_t len) {
    std::ostringstream oss;
    for (size_t i = 0; i < len; ++i) {
        oss << std::hex << std::setw(2) << std::setfill('0') << (int)data[i];
    }
    return oss.str();
}

void Util::hex_to_bytes(const std::string& hex, unsigned char* bytes, size_t len) {
    for (size_t i = 0; i < len; ++i) {
        std::string byteStr = hex.substr(i * 2, 2);
        bytes[i] = (unsigned char)strtol(byteStr.c_str(), NULL, 16);
    }
}

bool Util::aes_encrypt(const std::string& plaintext, const unsigned char* key, std::string& ciphertext) {
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        std::cerr << "Error initializing cipher context" << std::endl;
        return false;
    }

    unsigned char iv[AES_BLOCK_SIZE];
    RAND_bytes(iv, AES_BLOCK_SIZE);

    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv) != 1) {
        std::cerr << "Error in EncryptInit" << std::endl;
        EVP_CIPHER_CTX_free(ctx);
        return false;
    }

    std::vector<unsigned char> encrypted(plaintext.size() + AES_BLOCK_SIZE);
    int len, encrypted_len = 0;

    if (EVP_EncryptUpdate(ctx, encrypted.data(), &len, (const unsigned char*)plaintext.c_str(), plaintext.size()) != 1) {
        std::cerr << "Error in EncryptUpdate" << std::endl;
        EVP_CIPHER_CTX_free(ctx);
        return false;
    }
    encrypted_len += len;

    if (EVP_EncryptFinal_ex(ctx, encrypted.data() + encrypted_len, &len) != 1) {
        std::cerr << "Error in EncryptFinal" << std::endl;
        EVP_CIPHER_CTX_free(ctx);
        return false;
    }
    encrypted_len += len;
    EVP_CIPHER_CTX_free(ctx);

    ciphertext = to_hex(iv, AES_BLOCK_SIZE) + to_hex(encrypted.data(), encrypted_len);
    return true;
}

bool Util::aes_decrypt(const std::string& ciphertext, const unsigned char* key, std::string& plaintext) {
    if (ciphertext.size() < AES_BLOCK_SIZE * 2) {
        std::cerr << "Invalid ciphertext size" << std::endl;
        return false;
    }

    unsigned char iv[AES_BLOCK_SIZE];
    hex_to_bytes(ciphertext.substr(0, AES_BLOCK_SIZE * 2), iv, AES_BLOCK_SIZE);

    std::vector<unsigned char> encrypted((ciphertext.size() - AES_BLOCK_SIZE * 2) / 2);
    hex_to_bytes(ciphertext.substr(AES_BLOCK_SIZE * 2), encrypted.data(), encrypted.size());

    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        std::cerr << "Error initializing cipher context" << std::endl;
        return false;
    }

    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv) != 1) {
        std::cerr << "Error in DecryptInit" << std::endl;
        EVP_CIPHER_CTX_free(ctx);
        return false;
    }

    std::vector<unsigned char> decrypted(encrypted.size() + AES_BLOCK_SIZE);
    int len, decrypted_len = 0;

    if (EVP_DecryptUpdate(ctx, decrypted.data(), &len, encrypted.data(), encrypted.size()) != 1) {
        std::cerr << "Error in DecryptUpdate" << std::endl;
        EVP_CIPHER_CTX_free(ctx);
        return false;
    }
    decrypted_len += len;

    if (EVP_DecryptFinal_ex(ctx, decrypted.data() + decrypted_len, &len) != 1) {
        std::cerr << "Error in DecryptFinal" << std::endl;
        EVP_CIPHER_CTX_free(ctx);
        return false;
    }
    decrypted_len += len;
    EVP_CIPHER_CTX_free(ctx);

    plaintext = std::string(decrypted.begin(), decrypted.begin() + decrypted_len);


    plaintext = std::string(plaintext.c_str());

    return true;
}

