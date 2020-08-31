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
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurants(name, user_id)
VALUES ('Restaurant_1', 100000),
       ('Restaurant_2', 100000),
       ('Restaurant_3', 100001),
       ('Restaurant_4', 100000),
       ('Restaurant_5', 100001);

INSERT INTO menu(name, price, restaurant_id)
VALUES ('dish_1_1', 1000, 100002),
       ('dish_2_1', 1001, 100002),
       ('dish_3_1', 1002, 100002),

       ('dish_1_2', 1010, 100003),
       ('dish_2_2', 1011, 100003),
       ('dish_3_2', 1012, 100003),

       ('dish_1_3', 1110, 100004),
       ('dish_2_3', 1111, 100004),
       ('dish_3_3', 1112, 100004),

       ('dish_1_4', 1110, 100005),
       ('dish_2_4', 1111, 100005),

       ('dish_1_5', 1113, 100006),
       ('dish_2_5', 1114, 100006);