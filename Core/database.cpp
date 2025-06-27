#include "database.h"
#include <iostream>

Database::Database(const std::string& type) {
    try {
        driver = get_driver_instance();
        con = driver->connect(server, username, password);

        if (type == "database") {
            con->setSchema(database);
        }
       
        if (type == "publicKey") {
            con->setSchema(publicKey);
        }
    }
    catch (sql::SQLException& e) {
        std::cerr << "MySQL Connection Failed: " << e.what() << std::endl;
        exit(1);
    }
}

Database::~Database() {
    delete con;
}


bool Database::storeKey(const std::string& key, int& id,std::string keyType) {
    try {
        sql::PreparedStatement* pstmt = con->prepareStatement("INSERT INTO "+ keyType +" (aes_key)VALUES(? )");
        pstmt->setString(1, key);
        pstmt->executeUpdate();
        delete pstmt;

        sql::Statement* stmt = con->createStatement();
        sql::ResultSet* res = stmt->executeQuery("SELECT LAST_INSERT_ID()");
        if (res->next()) {
            id = res->getInt(1);
        }
        delete res;
        delete stmt;
        return true;
    }
    catch (sql::SQLException& e) {
        std::cerr << "Error storing key: " << e.what() << std::endl;
        return false;
    }
}

std::string Database::getKeyById(int id, std::string typekey) {
    try {
        sql::PreparedStatement* pstmt = con->prepareStatement("SELECT aes_key FROM "+ typekey +" WHERE id = ? ");
        pstmt->setInt(1, id);
        sql::ResultSet* res = pstmt->executeQuery();

        std::string key = "";
        if (res->next()) {
            key = res->getString("aes_key");
        }

        delete res;
        delete pstmt;
        return key;
    }
    catch (sql::SQLException& e) {
        std::cerr << "Error fetching key: " << e.what() << std::endl;
        return "";
    }
}
