DROP TABLE menu IF EXISTS;
DROP TABLE restaurants IF EXISTS
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;

DROP SEQUENCE  global_seq IF EXISTS;


CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id                 INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name               VARCHAR(255)                                                NOT NULL,
    email              VARCHAR(255)                                                NOT NULL,
    password           VARCHAR(255)                                             NOT NULL,
    registration       TIMESTAMP           DEFAULT now()                        NOT NULL,
    vote               BOOLEAN             DEFAULT TRUE                         NOT NULL,
    vote_time          TIMESTAMP           DEFAULT '1000-01-30 10:00:00.000000' NOT NULL,
    vote_restaurant_id INTEGER             DEFAULT 0

);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR (255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id       INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    user_id INTEGER NOT NULL,
    name    VARCHAR (255) NOT NULL,
--     voteSum INTEGER             DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE

);

CREATE TABLE menu
(
    id             INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    restaurant_id INTEGER          NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    price         DOUBLE PRECISION not null,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_restaurant_idx ON menu (id, restaurant_id);
