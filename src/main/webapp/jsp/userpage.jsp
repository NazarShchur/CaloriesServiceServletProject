<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="${param.lang}">
<head>
    <title>UserPage</title>
    <meta charset="UTF-8">
</head>
<jsp:include page="parts/header.jsp"/>
<body>
<h1><fmt:message key="hello"/> <c:out value="${sessionScope.user.login}"/> </h1>
<form method="post" action="${pageContext.request.contextPath}/app/logout">
    <input type="submit" value="<fmt:message key="logout"/>">
</form>
</body>
</html>
