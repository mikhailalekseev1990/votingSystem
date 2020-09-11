<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
</head>
<div>
    <h3><a href="profile">Register</a></h3>
</div>
<body>
<form action="spring_security_check" method="POST">
    <label for="username">User Name:</label>
    <input id="username" name="username" type="text"/>
    <label for="password">Password:</label>
    <input id="password" name="password" type="password"/>
    <input type="submit" value="Log In"/>
</form>

<div>
    <c:if test="${param.error}">
        <div class="error" style="color: red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
    </c:if>
    <c:if test="${not empty param.message}">
       <div class="message" style="color: blue">${param.message} user: ${param.username}</div>
    </c:if>
</div>
<br>
<br>
<div>
    Admin | admin@gmail.com | admin
</div>
<div>
    User | user@yandex.ru | user
</div>
<div>
    RESTAURANT1_ADMIN | admin@restaurant1.ru | restaurant1
</div>
<div>
    RESTAURANT2_ADMIN | admin@restaurant2.ru | restaurant2
</div>
<div>
    RESTAURANT3_ADMIN | admin@restaurant3.ru | restaurant3
</div>

</body>
</html>