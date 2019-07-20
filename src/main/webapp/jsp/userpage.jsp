<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denla
  Date: 19.07.2019
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
<h1>Hello <c:out value="${sessionScope.user.login}"/> </h1>
<form method="post" action="${pageContext.request.contextPath}/app/logout">
    <input type="submit" value="logout">
</form>
</body>
</html>
