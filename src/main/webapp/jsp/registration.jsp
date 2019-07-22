<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html lang="${param.lang}">
<head>
    <title>Registration</title>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<h1><fmt:message key="registration"/></h1>

<form method="post" action="${pageContext.request.contextPath}/app/register">
    <c:if test="${param.notUniqueLogin == true}">
        <p><fmt:message key="login.occupied"/></p>
    </c:if>
    <c:if test="${param.regexLogin == false}">
        <p><fmt:message key="login.regex.err"/></p>
    </c:if>
    <input type="text" name="login" placeholder="<fmt:message key="login"/>"
           <c:if test="${param.notUniqueLogin == true}">value="${param.login}"</c:if>><br/>
    <c:if test="${param.regexPassword == false}">
        <p><fmt:message key="password.regex.err"/></p>
    </c:if>
    <input type="password" name="password" placeholder="<fmt:message key="password"/>"><br/>
    <input type="submit" value="<fmt:message key="register"/>">
</form>
</body>
</html>
