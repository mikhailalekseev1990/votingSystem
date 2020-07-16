<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Restaurant</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>
    <jsp:useBean id="restaurant" class="ru.javawebinar.restaurant.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <dd><input type="hidden" name="restId" value="${restaurant.id}"></dd>
        <div>restId: ${restaurant.id == null ? "null" : restaurant.id}</div>
        <dl>
            <dt>Restaurant:</dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
        </dl>
        <dl>
        <br><br>
        <a href="dishes?action=create&restId=${restaurant.id}">Add Dish</a>
        <br><br>
        </dl>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>ID</th>
                <th>Rest id</th>
                <th>Dish</th>
                <th>Price</th>
            </tr>
            </thead>

            <c:forEach items="${restaurant.dishes}" var="dish">
                <jsp:useBean id="dish" type="ru.javawebinar.restaurant.model.Dish"/>
                <p>
                    <tr>
                        <td>${dish.id}</td>
                        <td>${dish.restaurant.name}</td>
                        <td>${dish.dish}</td>
                        <td>${dish.price}</td>
                        <td><a href="dishes?action=update&dishId=${dish.id}&restId=${restaurant.id}">Update</a></td>
                        <td><a href="dishes?action=delete&dishId=${dish.id}&restId=${restaurant.id}">Delete</a></td>
                    </tr>

                </p>

            </c:forEach>
        </table>
        <%--        <form action="restaurant" method="post">--%>
        <%--            <p>--%>
        <%--                Dish Id:--%>
        <%--                <input type="number" name="id" value="<c:out value="${id}"/> ">--%>
        <%--                Dish name--%>
        <%--                <input type="text-local" name="name" value="<c:out value="${dishName}"/> ">--%>
        <%--                Dish price--%>
        <%--                <input type="number" name="price" value="<c:out value="${price}"/>">--%>
        <%--              --%>
        <%--            <p>--%>
        <%--                <input type="submit" name="submit" value="add"></p>--%>
        <%--            <p>--%>
        <%--                <input type="submit" name="submit" value="update"></p>--%>
        <%--            <p>--%>
        <%--                <input type="submit" name="submit" value="delete"></p>--%>
        <%--            <p>--%>

        <%--        </form>--%>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>