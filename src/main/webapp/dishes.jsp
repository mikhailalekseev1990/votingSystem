<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dish</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create dish' : 'Edit dish'}</h2>
    <jsp:useBean id="dish" class="ru.javawebinar.restaurant.model.Dish" scope="request"/>
    <c:set var = "restaurantId" value='${requestScope["restaurantId"]}' scope="request"/>
    <form method="post" action="dishes">
        <dd><input type="hidden" name="dishId" value="${dish.id}"></dd>
        <dd><input type="hidden" name="restId" value="${restaurantId}"></dd>
        <div>restId ${restaurantId}</div>
        <div>dishId ${dish.id}</div>
        <dl>
            <dt>Dish:</dt>
            <dd><input type="text" value="${dish.name}" name="nameDish" required></dd>
            <dd><input type="text" value="${dish.price}" name="price" required></dd>

        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>