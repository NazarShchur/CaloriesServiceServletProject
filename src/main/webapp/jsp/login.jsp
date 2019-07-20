<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<c:if test="${param.wrongInput == true}">
    <p>Wrong login or password</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/app/auth">
    <input type="text" name="login"><br/>
    <input type="password" name="password"><br/>
    <input type="submit" value="login">
</form>
</body>
</html>
