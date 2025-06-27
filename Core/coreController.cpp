#include <iostream>
#include <httplib.h>
#include "util.h"
#include "database.h"
#include <nlohmann/json.hpp>
#include <thread>
#include <chrono>

#define RED     "\033[1;31m"
#define GREEN   "\033[1;32m"
#define BLUE    "\033[1;34m"
#define RESET   "\033[0m"
#define ORANGE  "\033[38;5;214m"

using json = nlohmann::json;
using namespace httplib;
using namespace std;


void showAnimatedBanner() {
    std::string banner = RED  R"(
  _____                       ______  __ 
 |  __ \                     |  ____|/ _|
 | |__) |_ _ _ __ ___  __ _  | |__  | |_ 
 |  ___/ _` | '__/ __|/ _` | |  __| |  _|
 | |  | (_| | |  \__ \ (_| | | |____| |  
 |_|   \__,_|_|  |___/\__,_| |______|_|        
    )" RESET;

    for (char c : banner) {
        std::cout << c << std::flush;
        std::this_thread::sleep_for(std::chrono::milliseconds(5));
    }
    std::cout << GREEN "\n Secure API is Starting..." << std::endl;
}

void showTable(int key_id, const std::string& encrypted_text) {
    int width = std::max(12, (int)encrypted_text.length());
    std::cout << ORANGE "+--------------+----------------------------------+\n";
    std::cout << ORANGE "| Insert Into Database:                           |\n";
    std::cout << ORANGE "+--------------+----------------------------------+\n";
    std::cout << BLUE "+--------------+----------------------------------+\n";
    std::cout << "| Stored Key ID | " << std::setw(width) << key_id << " |\n";
    std::cout << "| Encrypted Text | " << std::setw(width) << encrypted_text << " |\n";
    std::cout << "+--------------+----------------------------------+\n" RESET << std::endl;
}

int main() {

    showAnimatedBanner();

    Server svr;
    Database db("database");

    Database pub("publicKey");

    //Encrypt-Service
    svr.Post("/encrypt", [&](const Request& req, Response& res) {
        string user_input = req.body;

        unsigned char aes_key[16];
        Util::generate_aes_key(aes_key);
        string key_hex = Util::to_hex(aes_key, sizeof(aes_key));

        int key_id;
        if (!db.storeKey(key_hex, key_id, "PrivateKey")) {
            json error_json = { {"status", "error"}, {"message", "Error storing key!"} };
            res.set_content(error_json.dump(), "application/json; charset=utf-8");
            return;
        }

        string encrypted_text;
        if (Util::aes_encrypt(user_input, aes_key, encrypted_text)) {

            showTable(key_id, encrypted_text);

            pub.storeKey(encrypted_text, key_id,"PublicKey");

            json response_json = {
                    {"status", "success"},
                    {"key_id", to_string(key_id)},
                    {"encrypted_text", encrypted_text}
            };

            res.set_content(response_json.dump(), "application/json; charset=utf-8");
        }
        else {
            json error_json = { {"status", "error"}, {"message", "Encryption failed"} };
            res.set_content(error_json.dump(), "application/json; charset=utf-8");
        }
        });

    //Decrypt-Service
    svr.Post("/decrypt", [&](const Request& req, Response& res) {
        auto id = req.get_param_value("id");
        string encrypted_text = pub.getKeyById(stoi(id), "PublicKey");

        string key_hex = db.getKeyById(stoi(id),"PrivateKey");

        if (key_hex.empty()) {
            json error_json = { {"status", "error"}, {"message", "Key not found"} };
            res.set_content(error_json.dump(), "application/json; charset=utf-8");
            return;
        }

        unsigned char aes_key[16];
        Util::hex_to_bytes(key_hex, aes_key, sizeof(aes_key));

        string decrypted_text;
        if (Util::aes_decrypt(encrypted_text, aes_key, decrypted_text)) {
           
            json response_json = {
                {"status", "success"},
                {"decrypted_text", decrypted_text}
            };
            res.set_content(response_json.dump(), "application/json; charset=utf-8");
        }
        else {
            json error_json = { {"status", "error"}, {"message", "Decryption failed"} };
            res.set_content(error_json.dump(), "application/json; charset=utf-8");
        }
        });
 

    svr.listen("localhost", 8080);
    return 0;
}