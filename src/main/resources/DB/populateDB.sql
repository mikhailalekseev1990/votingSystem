DELETE
FROM user_roles;
DELETE
FROM restaurants;
DELETE
FROM users;
DELETE
FROM menu;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users(name, email, password)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User', 'user@yandex.ru', '{noop}user'),
       ('RESTAURANT1_ADMIN', 'admin@restaurant1.ru', '{noop}restaurant1'),
       ('RESTAURANT2_ADMIN', 'admin@restaurant2.ru', '{noop}restaurant2'),
       ('RESTAURANT3_ADMIN', 'admin@restaurant3.ru', '{noop}restaurant3');

INSERT INTO user_roles (role, user_id)
VALUES ('ADMIN', 100000),
       ('USER', 100001),
       ('RESTAURANT_ADMIN', 100002),
       ('RESTAURANT_ADMIN', 100003),
       ('RESTAURANT_ADMIN', 100004);

INSERT INTO restaurants(name, user_id)
VALUES ('Restaurant_1', 100002),
       ('Restaurant_2', 100002),
       ('Restaurant_3', 100003),
       ('Restaurant_4', 100003),
       ('Restaurant_5', 100004);

INSERT INTO menu(name, price, restaurant_id)
VALUES ('dish_1_1', 1000, 100005),
       ('dish_2_1', 1001, 100005),
       ('dish_3_1', 1002, 100005),

       ('dish_1_2', 1010, 100006),
       ('dish_2_2', 1011, 100006),
       ('dish_3_2', 1012, 100006),

       ('dish_1_3', 1110, 100007),
       ('dish_2_3', 1111, 100007),
       ('dish_3_3', 1112, 100007),

       ('dish_1_4', 1110, 100008),
       ('dish_2_4', 1111, 100008),

       ('dish_1_5', 1113, 100009),
       ('dish_2_5', 1114, 100009);