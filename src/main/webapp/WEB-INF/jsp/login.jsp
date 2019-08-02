<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<w:wrapper>
    <h1 class="col-lg-12 text-center"><fmt:message key="login"/></h1>
    <c:if test="${param.wrongInput == true}">
        <p><fmt:message key="wrong.login.password"/></p>
    </c:if>
    <form class="col-lg-12 text-center regform" method="post" action="${pageContext.request.contextPath}/app/auth">

        <label class="row">
       <span class="col-lg-3">
           <fmt:message key="login"/>
       </span>
            <div class="col-lg-9">
                <input type="text" name="login">
            </div>
        </label>
        <label class="row">
        <span class="col-lg-3">
            <fmt:message key="password"/>
        </span>
            <div class="col-lg-9">
                <input type="password" name="password">
            </div>
        </label>
        <label class="col-lg-8">
            <input type="submit" value="<fmt:message key="log.in"/>">
        </label>
    </form>
</w:wrapper>
