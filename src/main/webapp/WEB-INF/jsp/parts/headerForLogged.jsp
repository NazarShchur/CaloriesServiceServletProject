<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<div class="myheader">
    <div class="row">
        <a href="${pageContext.request.contextPath}/app/main" class="col-lg-2 logo">Calories</a>
        <a href="${pageContext.request.contextPath}/app/userpage" class="col-lg-2"><fmt:message key="user.profile"/></a>
        <a href="${pageContext.request.contextPath}/app/logout" class="col-lg-2 offset-4"><fmt:message key="logout"/></a>

        <div class="col-lg-2">
            <a href="?locale=eng">ENG</a>
            <a href="?locale=ua">UA</a>
        </div>
    </div>
</div>
