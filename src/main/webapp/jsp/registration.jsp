
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<c:if test="${param.notUnique == true}">
    <p>Login occupied</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/app/register">
    <input type="text" name="login"><br/>
    <input type="password" name="password"><br/>
    <input type="submit" value="register">
</form>
</body>
</html>
