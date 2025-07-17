cmake_minimum_required(VERSION 3.20)
project(SecureAPI)

set(CMAKE_CXX_STANDARD 17)

# Find OpenSSL
find_package(OpenSSL REQUIRED)

# Include directories
include_directories(
        /opt/homebrew/include
        /opt/homebrew/include/mysql-cppconn-8
)

# Link directories
link_directories(
        /opt/homebrew/lib
)

# Add executable
add_executable(SecureAPI main.cpp util.cpp database.cpp)

# Link libraries
target_link_libraries(SecureAPI
        OpenSSL::SSL
        OpenSSL::Crypto
        mysqlcppconn8
)