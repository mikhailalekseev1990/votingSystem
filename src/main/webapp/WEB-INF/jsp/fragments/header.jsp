<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>header</title>
    <link rel="stylesheet" href="../../css/style.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>

</head>
<body>
<h3><a href="logout">Logout</a></h3>
<sec:authorize access="hasRole('ADMIN')">
<h3><a href="users">Users</a></h3>
</sec:authorize>

</body>
</html>
