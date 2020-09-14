<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <hr/>
    <h2>Restaurants</h2>
    <hr/>
    <sec:authorize access="hasRole('RESTAURANT_ADMIN')">
        <a href="${pageContext.request.contextPath}/restaurants/create">Add Restaurant</a>
    <br><br>
    </sec:authorize>
    <c:set var="user" value='${requestScope["user"]}' scope="request"/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Menu</th>
<%--            <th></th>--%>
<%--            <th></th>--%>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <%--            <c:if test="${restaurant.id==restaurant.user.vote_restaurant_id}">--%>
            <%--                background: maroon;--%>
            <%--            </c:if>--%>
            <jsp:useBean id="restaurant" type="ru.javawebinar.restaurant.model.Restaurant"/>
            <input type="hidden" name="r_id" value="${restaurant.id}">
            <tr style="color:${restaurant.id==user.vote_restaurant_id ? 'blue' : 'red'}">
                <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
                <td>
                    <sec:authorize access="hasRole('RESTAURANT_ADMIN')">
                        <a href="dish/create/${restaurant.id}">Add Dish</a>
                    </sec:authorize>
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
                                    <%--                                <c:if test="${restaurant.id==dish.restaurant.id}">--%>
                                <tr>
                                    <td>${dish.id}</td>
                                    <td>${dish.restaurant.id}</td>
                                    <td>${dish.name}</td>
                                    <td>${dish.price}</td>
                                    <sec:authorize access="hasRole('RESTAURANT_ADMIN')">
                                        <td><a href="dish/update/${restaurant.id}/${dish.id}">Update</a>
                                        </td>
                                        <td><a href="dish/delete/${restaurant.id}/${dish.id}">Delete</a>
                                        </td>
                                    </sec:authorize>
                                </tr>
                                    <%--                                </c:if>--%>
                            </p>
                        </c:forEach>
                    </table>
                </td>
                <sec:authorize access="hasRole('RESTAURANT_ADMIN')">
                    <td><a href="restaurants/update/${restaurant.id}">Update</a></td>
                    <td><a href="restaurants/delete/${restaurant.id}">Delete</a></td>
                </sec:authorize>
                <sec:authorize access="hasRole('USER')">
                    <td><a href="restaurants/vote?r_id=${restaurant.id}"> Vote </a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>