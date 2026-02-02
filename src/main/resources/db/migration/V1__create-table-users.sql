create table users(
       id UUID PRIMARY KEY,
       name VARCHAR(20),
       email VARCHAR(100) UNIQUE,
       password VARCHAR(100)
);