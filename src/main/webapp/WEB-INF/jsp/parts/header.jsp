<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<div class="myheader">
    <div class="row">
        <a href="${pageContext.request.contextPath}/app/main" class="col-lg-2 logo">Calories</a>
        <a href="${pageContext.request.contextPath}/app/login" class="col-lg-2"><fmt:message key="login"/></a>
        <a href="${pageContext.request.contextPath}/app/registration" class="col-lg-2"><fmt:message key="registration"/></a>
        <div class="col-lg-2 offset-4">
            <a href="?locale=eng">ENG</a>
            <a href="?locale=ua">UA</a>
        </div>
    </div>
</div>
