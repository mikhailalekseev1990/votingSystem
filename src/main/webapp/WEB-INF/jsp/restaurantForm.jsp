<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<section>
    <h3><a href="profile.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>
    <jsp:useBean id="restaurant" class="ru.javawebinar.restaurant.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <dd><input type="hidden" name="r_id" value="${restaurant.id}"></dd>
        <div>r_id: ${restaurant.id == null ? "null" : restaurant.id}</div>
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