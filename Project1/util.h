#ifndef UTIL_H
#define UTIL_H

#include <string>

class Util {
public:
    static void generate_aes_key(unsigned char* key);
    static bool aes_encrypt(const std::string& plaintext, const unsigned char* key, std::string& ciphertext);
    static bool aes_decrypt(const std::string& ciphertext, const unsigned char* key, std::string& plaintext);
    static std::string to_hex(const unsigned char* data, size_t len);
    static void hex_to_bytes(const std::string& hex, unsigned char* bytes, size_t len);
};

#endif // UTIL_H
