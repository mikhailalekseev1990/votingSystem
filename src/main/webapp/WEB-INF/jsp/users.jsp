<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<head>
    <title>Users</title>
   </head>
<body>
<section>
    <hr/>
        <h2>Users</h2>
        <hr/>
        <a href="${pageContext.request.contextPath}/users/create">Add user</a>
        <br><br>
    <c:set var="user" value='${requestScope["users"]}' scope="request"/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Enabled</th>
            <th>Registration</th>
            <th>Vote</th>
            <th>Vote Time</th>
            <th>Vote_restaurant_id</th>
            <th>Roles</th>

        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <%--            <c:if test="${restaurant.id==restaurant.user.vote_restaurant_id}">--%>
            <%--                background: maroon;--%>
            <%--            </c:if>--%>
            <jsp:useBean id="user" type="ru.javawebinar.restaurant.model.User"/>
            <input type="hidden" name="u_id" value="${user.id}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.enabled}</td>
                <td>${user.registration}</td>
                <td>${user.vote}</td>
                <td>${user.voteTime}</td>
                <td>${user.vote_restaurant_id}</td>
                <td>${user.roles}</td>

                <td><a href="users/update?u_id=${user.id}">Update</a></td>
                <td><a href="users/delete?u_id=${user.id}">Delete</a></td>


            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>