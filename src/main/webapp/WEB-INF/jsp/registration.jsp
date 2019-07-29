<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<w:wrapper>
    <h1 class="col-lg-12 text-center"><fmt:message key="registration"/></h1>
    <c:if test="${param.unacceptableData == true}">
        <p><fmt:message key="unacceptableData"/></p>
    </c:if>
    <form class="col-lg-12 regform" method="post" action="${pageContext.request.contextPath}/app/register">
        <c:if test="${param.notUniqueLogin == true}">
            <p><fmt:message key="login.occupied"/></p>
        </c:if>
        <label class="row">
            <span class="col-lg-3"><fmt:message key="login"/></span>
            <div class="col-lg-9">
                <c:if test="${param.checkLogin == false}">
                    <p><fmt:message key="login.regex.err"/></p>
                </c:if>
                <input type="text" name="login" value="${param.login}" required>
            </div>
        </label>

        <label class="row">
            <span class="col-lg-3"><fmt:message key="password"/></span>
            <div class="col-lg-9">
                <c:if test="${param.checkPassword == false}">
                    <p><fmt:message key="password.regex.err"/></p>
                </c:if>
                <input type="password" name="password" required>
            </div>
        </label>


        <label class="row">
            <span class="col-lg-3"><fmt:message key="age"/></span>
            <div class="col-lg-9">
                <c:if test="${param.checkAge == false}">
                    <p><fmt:message key="age.err"/></p>
                </c:if>
                <input type="number" name="age" value="${param.age}" required min="1" max="120">
            </div>
        </label>


        <label class="row">
            <span class="col-lg-3"><fmt:message key="weight"/></span>
            <div class="col-lg-9">
                <c:if test="${param.checkWeight == false}">
                    <p><fmt:message key="weight.err"/></p>
                </c:if>
                <input type="number" name="weight" value="${param.weight}" required min="6" max="600">
            </div>
        </label>

        <label class="row">
            <span class="col-lg-3"><fmt:message key="height"/></span>
            <div class="col-lg-9">
                <c:if test="${param.checkHeight == false}">
                    <p><fmt:message key="height.err"/></p>
                </c:if>
                <input type="number" name="height" value="${param.height}" required min="40" max="250">
            </div>
        </label>
        <label class="row">
            <span class="col-lg-3"><fmt:message key="gender"/></span>
            <div class="col-lg-9">
                <select name="gender" required>
                    <option value="Man" selected><fmt:message key="Man"/></option>
                    <option value="Woman"><fmt:message key="Woman"/></option>

                </select>
            </div>
        </label>
        <label class="row">
            <span class="col-lg-3"><fmt:message key="lifestyle"/></span>
            <div class="col-lg-9">
                <select name="lifeStyle" required>
                    <option value="VeryLowActivity" selected><fmt:message key="VeryLowActivity"/></option>
                    <option value="LowActivity"><fmt:message key="LowActivity"/></option>
                    <option value="NormalActivity"><fmt:message key="NormalActivity"/></option>
                    <option value="HighActivity"><fmt:message key="HighActivity"/></option>
                    <option value="VeryHighActivity"><fmt:message key="VeryHighActivity"/></option>
                </select>
            </div>
        </label>
        <label class="col-lg-12"><input type="submit" value="<fmt:message key="register"/>"></label>
    </form>
</w:wrapper>
