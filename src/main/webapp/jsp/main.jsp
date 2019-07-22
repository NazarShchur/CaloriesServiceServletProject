<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<head>
    <title>Main</title>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="parts/header.jsp"/>

<h1>Main</h1>
<a href="${pageContext.request.contextPath}/app/registration"><fmt:message key="registration"/></a>
<a href="${pageContext.request.contextPath}/app/login"><fmt:message key="log.in"/></a>
</body>
</html>
