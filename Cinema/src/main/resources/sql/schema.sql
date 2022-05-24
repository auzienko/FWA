DROP TABLE IF EXISTS users;

CREATE TABLE users{
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    phone_number VARCHAR,
    email VARCHAR,
    password VARCHAR
    };