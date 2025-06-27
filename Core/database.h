#ifndef DATABASE_H
#define DATABASE_H

#include <cppconn/driver.h>
#include <cppconn/exception.h>
#include <cppconn/prepared_statement.h>
#include <string>

class Database {
private:
    sql::Driver* driver;
    sql::Connection* con;
    const std::string server = "tcp://127.0.0.1:3306";
    const std::string username = "root";
    const std::string password = "P@ssw0rd!2023";
    const std::string database = "SecureDB";
    const std::string publicKey = "PublicDB";

public:
    Database(const std::string& type);
    ~Database();
    bool storeKey(const std::string& key, int& id, std::string typekey);
    std::string getKeyById(int id, std::string typekey);
};

#endif // DATABASE_H
