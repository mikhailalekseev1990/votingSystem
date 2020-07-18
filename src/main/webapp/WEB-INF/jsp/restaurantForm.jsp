<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Restaurant</title>
    <link rel="stylesheet" href="../../css/style.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>
    <jsp:useBean id="restaurant" class="ru.javawebinar.restaurant.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <dd><input type="hidden" name="restId" value="${restaurant.id}"></dd>
        <div>restId: ${restaurant.id == null ? "null" : restaurant.id}</div>
        <dl>
            <dt>Restaurant:</dt>
            <dd><input type="text" value="${restaurant.name}" name="restaurant_name" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>