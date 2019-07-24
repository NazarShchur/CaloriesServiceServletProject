<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<w:wrapper>
<h1 class="col-lg-12"><fmt:message key="registration"/></h1>
<c:if test="${param.unacceptableData == true}">
    <p><fmt:message key="unacceptableData"/></p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/app/register">
    <c:if test="${param.notUniqueLogin == true}">
        <p><fmt:message key="login.occupied"/></p>
    </c:if>
    <div class="col-lg-12">
        <label>
        <h3><fmt:message key="login"/></h3>
        <c:if test="${param.checkLogin == false}">
            <p><fmt:message key="login.regex.err"/></p>
        </c:if>
        <input type="text" name="login" value="${param.login}" required>
        </label>
    </div>
    <div class="col-lg-12">
        <label>
        <h3><fmt:message key="password"/></h3>
        <c:if test="${param.checkPassword == false}">
            <p><fmt:message key="password.regex.err"/></p>
        </c:if>
        <input type="password" name="password" required>
        </label>
    </div>
    <div class="col-lg-12">
        <label>
        <h3><fmt:message key="age"/></h3>
        <c:if test="${param.checkAge == false}">
            <p><fmt:message key="age.err"/></p>
        </c:if>
       <input type="number" name="age"  value="${param.age}" required min="1" max="120">
        </label>
    </div>
    <div class="col-lg-12">
        <label>
        <h3><fmt:message key="weight"/></h3>
        <c:if test="${param.checkWeight == false}">
            <p><fmt:message key="weight.err"/></p>
        </c:if>
        <input type="number" name="weight" value="${param.weight}" required min="6" max="600" >
        </label>
    </div>
    <div class="col-lg-12">
        <label>
        <h3><fmt:message key="height"/></h3>
        <c:if test="${param.checkHeight == false}">
            <p><fmt:message key="height.err"/></p>
        </c:if>
        <input type="number" name="height" value="${param.height}"required min="40" max="250"  >
        </label>
    </div>

    <div class="col-lg-12">
        <h3><fmt:message key="gender"/></h3>
        <label><input type="radio" name="gender" value="Man" checked><fmt:message key="man"/></label>
        <label><input type="radio" name="gender" value="Woman"><fmt:message key="woman"/></label>
    </div>
    <div class="col-lg-12">
        <h3><fmt:message key="lifestyle"/></h3>
        <label><input type="radio" name="lifeStyle" value="VeryLowActivity" checked/><fmt:message key="veryLowActivity"/></label>
        <label><input type="radio" name="lifeStyle" value="LowActivity" /><fmt:message key="lowActivity"/></label>
        <label><input type="radio" name="lifeStyle" value="NormalActivity" /><fmt:message key="normalActivity"/></label>
        <label><input type="radio" name="lifeStyle" value="HighActivity" /><fmt:message key="highActivity"/></label>
        <label><input type="radio" name="lifeStyle" value="VeryHighActivity"/><fmt:message key="veryHighActivity"/></label>
    </div>
    <input type="submit" value="<fmt:message key="register"/>">
</form>
</w:wrapper>
