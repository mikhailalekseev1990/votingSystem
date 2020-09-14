### curl samples 

#### get All Users
`curl -s http://localhost:8080/restaurant/rest/users --user admin@gmail.com:admin`

#### get User 100001
`curl -s http://localhost:8080/restaurant/rest/users/100001 --user admin@gmail.com:admin`

#### get User 100002 with Restaurants
`curl -s http://localhost:8080/restaurant/rest/users/100002/with-restaurants  --user admin@gmail.com:admin`

#### register Users//TODO
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

