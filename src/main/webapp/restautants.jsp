<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <hr/>
    <a href="restaurants?action=create">Add Restaurant</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Menu</th>
            <th>voteSum</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
        <jsp:useBean id="restaurant" type="ru.javawebinar.restaurant.model.Restaurant"/>
        <tr>

            <td>${restaurant.id}</td>
            <td>${restaurant.name}</td>
            <td>${restaurant.voteSum}</td>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Dish</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <c:forEach items="${menu}" var="menus">
                    <jsp:useBean id="menu" type="ru.javawebinar.restaurant.model.Dish"/>
                    <tr>
                        <td>${menu.id}</td>
                        <td>${menu.dish}</td>
                        <td>${menu.price}</td>
                    </tr>
                </table>
            </td>
            <td><a href="restaurant?action=update&id=${restaurant.id}">Update</a></td>
            <td><a href="restaurant?action=delete&id=${restaurant.id}">Delete</a></td>
        </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>