<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h3><a href="profile.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'dish_create' ? 'Create dish' : 'Edit dish'}</h2>
    <jsp:useBean id="dish" class="ru.javawebinar.restaurant.model.Dish" scope="request"/>
    <c:set var="restaurantId" value='${requestScope["restaurantId"]}' scope="request"/>
    <form method="post" action="dish">
        <dd><input type="hidden" name="d_id" value="${dish.id}"></dd>
        <dd><input type="hidden" name="r_id" value="${restaurantId}"></dd>
        <div>r_id ${restaurantId}</div>
        <div>d_id ${dish.id}</div>
        <dl>
            <dt>Dish:</dt>
            <dd><input type="text" value="${dish.name}" name="dish_name" required></dd>
            <dd><input type="text" value="${dish.price}" name="dish_price" required></dd>

        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>