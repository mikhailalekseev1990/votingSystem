<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>register</title>
    <link rel="stylesheet" href="../../css/style.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>

</head>
<body>
<section>
    <h3><a href="login">Home</a></h3>
    <hr>
    <h2>${param.action == 'user_update' ? 'Edit user' : 'Create user'}</h2>
    <jsp:useBean id="user" class="ru.javawebinar.restaurant.model.User" scope="request"/>
    <form method="post" action="profile">
        <dd><input type="hidden" name="u_id" value="${user.id}"></dd>
        <dl>
            <dd>Name: <input type="text" value="${user.name}" name="user_name" required></dd>
            <dd>Email: <input type="text" value="${user.email}" name="user_email" required></dd>
            <dd>Password: <input type="password" value="${user.password}" name="user_password" required></dd>
            <dd>Role: <select name="user_role">
                <option value="USER">User</option>
                <option value="RESTAURANT_ADMIN">Restaurant admin</option>
                <sec:authorize access="hasRole('ADMIN')">
                <option value="ADMIN">Admin</option>
                </sec:authorize>
            </select>
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>