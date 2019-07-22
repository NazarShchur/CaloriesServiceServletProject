<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
</head>

<body>
<jsp:include page="parts/header.jsp"/>
<h1>Login</h1>
<c:if test="${param.wrongInput == true}">
    <p><fmt:message key="wrong.login.password"/> </p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/app/auth">
    <input type="text" name="login" placeholder="<fmt:message key="login"/>"><br/>
    <input type="password" name="password" placeholder="<fmt:message key="password"/>"><br/>
    <input type="submit" value="<fmt:message key="log.in"/>">
</form>
</body>
</html>
