DROP TABLE IF EXISTS users;
  
CREATE TABLE users (
user_id INT primary key,
name VARCHAR(250) NOT NULL,
email VARCHAR(250),
role VARCHAR(250)
);

DROP TABLE IF EXISTS books;
  
CREATE TABLE books (
book_id INT primary key,
book_name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS counter_party;
  
CREATE TABLE counter_party (
counter_party_id INT primary key ,
counter_party_name  VARCHAR(250) 
);

DROP TABLE IF EXISTS securities;
  
CREATE TABLE securities (
security_id INT primary key,
isin VARCHAR(250), 
cusip VARCHAR(250), 
issuer VARCHAR(250),
maturity_date DATE,
coupon FLOAT, 
type VARCHAR(250),
face_value INT,
status VARCHAR(25));

DROP TABLE IF EXISTS trades;
  
CREATE TABLE trades (
trade_id INT primary key,
book_id INT, 
cpid INT, 
sid INT,
quantity INT,
status VARCHAR(250) , 
price INT,
buy_sell INT,
trade_date DATE,
settlement_date DATE);