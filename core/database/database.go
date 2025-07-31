package database

import (
	"database/sql"
	_ "github.com/go-sql-driver/mysql"
	"log"
)

type Database struct {
	db *sql.DB
}

func NewDatabase(schema string) *Database {
	dsn := "root:P@ssw0rd!2023@tcp(127.0.0.1:3307)/" + schema
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Failed to connect to DB: %v", err)
	}
	return &Database{db: db}
}

func (d *Database) StoreKey(key string, table string) (int, error) {
	res, err := d.db.Exec("INSERT INTO "+table+" (aes_key) VALUES (?)", key)
	if err != nil {
		return 0, err
	}
	id, err := res.LastInsertId()
	return int(id), err
}

func (d *Database) GetKeyById(id int, table string) (string, error) {
	row := d.db.QueryRow("SELECT aes_key FROM "+table+" WHERE id = ?", id)
	var key string
	err := row.Scan(&key)
	return key, err
}

func (d *Database) StoreKeyWithID(data string, id int, table string) (int, error) {
	_, err := d.db.Exec("INSERT INTO "+table+" (id, aes_key) VALUES (?, ?)", id, data)
	return id, err
}

func (d *Database) GetLastPrivateKey(s string) (string, interface{}) {
	row := d.db.QueryRow("SELECT aes_key FROM " + s + " ORDER BY id DESC LIMIT 1")
	var key string
	err := row.Scan(&key)
	return key, err
}
