<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Restaurants</title>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <hr/>
    <a href="${pageContext.request.contextPath}/restaurants/create">Add Restaurant</a>
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
            <input type="hidden" name="r_id" value="${restaurant.id}">
            <tr>
                <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
                <td>
                    <a href="dish/dish_create?r_id=${restaurant.id}">Add Dish</a>

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
                                    <td>${dish.restaurant.id}</td>
                                    <td>${dish.dish}</td>
                                    <td>${dish.price}</td>
                                    <td><a href="dish/dish_update?d_id=${dish.id}&r_id=${restaurant.id}">Update</a>
                                    </td>
                                    <td><a href="dish/dish_delete?d_id=${dish.id}&r_id=${restaurant.id}">Delete</a>
                                    </td>
                                </tr>
                            </p>

                        </c:forEach>
                    </table>
                </td>

                <td>${restaurant.voteSum}</td>
                <td><a href="restaurants/update?r_id=${restaurant.id}">Update</a></td>
                <td><a href="restaurants/delete?r_id=${restaurant.id}">Delete</a></td>
                <td><a href="restaurants/vote?r_id=${restaurant.id}"> Vote </a></td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>