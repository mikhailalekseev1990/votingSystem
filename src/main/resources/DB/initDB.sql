DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;


CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name         VARCHAR                           NOT NULL,
    email        VARCHAR                           NOT NULL,
    password     VARCHAR                           NOT NULL,
    registration TIMESTAMP           DEFAULT now() NOT NULL,
    vote         BOOLEAN             DEFAULT TRUE  NOT NULL,
    voteTime     TIMESTAMP           DEFAULT now() NOT NULL

);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR NOT NULL,
    voteSum INTEGER DEFAULT 0
);

CREATE TABLE menu
(
    dish_id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER          NOT NULL,
    dish          VARCHAR          NOT NULL,
    price         DOUBLE PRECISION not null,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_restaurant_idx ON menu (dish_id, restaurant_id);
