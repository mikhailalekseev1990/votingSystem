The task is: Build a voting system for deciding where to have lunch.
====
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

The implement:
===
The project uses the HSQL database. Four tables are created at startup: users, users_role, menu, restaurants.

The project has three types of users (deviation from the technical task):
user - regular user (user can vote on restaurant and see all restaurants with menu),
restaurant_admin - restaurant's administrator(restaurant_admin can create/update/delete menu/restaurant, belonging himself)
admin - user's administrator(admin can see all users and delete user).

The data on the choice of the restaurant and the voting time are stored in the corresponding user fields.

The user of the application can select a role(user, restaurant_admin) during registration.

Running the application
==
Clone application or download and unzip zip-archive from github  <a href="https://github.com/mikhailalekseev1990/votingSystem>voting system</a>.
In the terminal, go to the folder with the application. 
Run the command "mvn clean package".
After message "BUILD SUCCESS" run command "mvn cargo:run". 
In the browser go to the address: http://localhost:8080/restaurant/

REST API
==

### curl samples 

#### get All Users
`curl -s http://localhost:8080/restaurant/rest/users --user admin@gmail.com:admin`

#### get User 100001
`curl -s http://localhost:8080/restaurant/rest/users/100001 --user admin@gmail.com:admin`

#### get User 100002 with Restaurants
`curl -s http://localhost:8080/restaurant/rest/users/100002/with-restaurants  --user admin@gmail.com:admin`

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password","role":"USER"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/profile`

#### get All Restaurants for User 100003
`curl -s http://localhost:8080/restaurant/rest/restaurants/ --user admin@restaurant2.ru:restaurant2`

#### get Restaurants 100007 for User 100003
`curl -s http://localhost:8080/restaurant/rest/restaurants/100007  --user admin@restaurant2.ru:restaurant2`

#### get Restaurant 100007 with Dishes for User 100003
`curl -s http://localhost:8080/restaurant/rest/restaurants/100007/with-dishes  --user admin@restaurant2.ru:restaurant2`

#### get Restaurant 100007 for User 100002 not found
`curl -s -v http://localhost:8080/restaurant/rest/restaurants/100007 --user admin@restaurant1.ru:restaurant1`

#### delete Restaurant 100006
`curl -s -X DELETE http://localhost:8080/restaurant/rest/restaurants/100006 --user admin@restaurant1.ru:restaurant1`

#### create Restaurant for User 100002
`curl -s -X POST -d '{"name":"Created Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/restaurants --user admin@restaurant1.ru:restaurant1`

#### update Restaurant 100005
`curl -s -X PUT -d '{"name":"Updated Restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/restaurant/rest/restaurants/100005 --user admin@restaurant1.ru:restaurant1`

#### get All Dishes for Restaurant 100005
`curl -s http://localhost:8080/restaurant/rest/restaurants/100005/dishes --user admin@restaurant1.ru:restaurant1`

#### get Dishes 100011
`curl -s http://localhost:8080/restaurant/rest/restaurants/100005/dishes/100011  --user admin@restaurant1.ru:restaurant1`

#### get Dishes 100015 for Restaurant 100007 not found
`curl -s -v http://localhost:8080/restaurant/rest/restaurants/100007/dishes/100015 --user admin@restaurant2.ru:restaurant2`

#### delete Dish 100011 for Restaurant 100005
`curl -s -X DELETE http://localhost:8080/restaurant/rest/restaurants/100005/dishes/100011 --user admin@restaurant1.ru:restaurant1`

#### create Dish for Restaurant 100005
`curl -s -X POST -d '{"name":"Created Dish","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/restaurants/100005/dishes --user admin@restaurant1.ru:restaurant1`

#### update Dish 100010 for Restaurant 100005
`curl -s -X PUT -d '{"name":"Updated Dish","price":3000}' -H 'Content-Type: application/json' http://localhost:8080/restaurant/rest/restaurants/100005/dishes/100010 --user admin@restaurant1.ru:restaurant1`


