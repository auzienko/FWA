DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_auth_history;

CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR,
    last_name    VARCHAR,
    phone_number VARCHAR,
    email        VARCHAR,
    password     VARCHAR
);
CREATE TABLE user_auth_history
(
    id        BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP,
    ip        VARCHAR,
    user_id   BIGINT
);

GRANT ALL ON SEQUENCE public.user_auth_history_id_seq TO cinema21;

GRANT ALL ON SEQUENCE public.users_id_seq TO cinema21;

GRANT ALL ON TABLE public.user_auth_history TO cinema21;

GRANT ALL ON TABLE public.users TO cinema21;