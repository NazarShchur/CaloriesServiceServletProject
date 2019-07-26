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
    <form class="col-lg-12 regform row" method="post" action="${pageContext.request.contextPath}/app/register">
        <c:if test="${param.notUniqueLogin == true}">
            <p><fmt:message key="login.occupied"/></p>
        </c:if>
            <label class="col-lg-6">
                <c:if test="${param.checkLogin == false}">
                    <p><fmt:message key="login.regex.err"/></p>
                </c:if>
                <input type="text" placeholder="<fmt:message key="login"/>" name="login" value="${param.login}" required>
            </label>

            <label class="col-lg-6">
                <c:if test="${param.checkPassword == false}">
                    <p><fmt:message key="password.regex.err"/></p>
                </c:if>
                <input type="password" placeholder="<fmt:message key="password"/>" name="password" required>
            </label>


            <label class="col-lg-4">
                <c:if test="${param.checkAge == false}">
                    <p><fmt:message key="age.err"/></p>
                </c:if>
                <input type="number" placeholder="<fmt:message key="weight"/>" name="age" value="${param.age}" required min="1" max="120">
            </label>


            <label class="col-lg-4">
                <c:if test="${param.checkWeight == false}">
                    <p><fmt:message key="weight.err"/></p>
                </c:if>
                <input type="number" placeholder="<fmt:message key="weight"/>" name="weight" value="${param.weight}" required min="6" max="600">
            </label>

            <label class="col-lg-4">
                <c:if test="${param.checkHeight == false}">
                    <p><fmt:message key="height.err"/></p>
                </c:if>
                <input type="number" placeholder="<fmt:message key="height"/>" name="height" value="${param.height}" required min="40" max="250">
            </label>
            <label class="col-lg-6">
                <select name="gender">
                    <option selected disabled><fmt:message key="gender"/></option>
                    <option value="Man"><fmt:message key="Man"/></option>
                    <option value="Woman"><fmt:message key="Woman"/></option>

                </select>
            </label>
            <label class="col-lg-6">
                <select name="lifeStyle">
                    <option selected disabled><fmt:message key="lifestyle"/></option>
                    <option value="VeryLowActivity"><fmt:message key="VeryLowActivity"/></option>
                    <option value="LowActivity"><fmt:message key="LowActivity"/></option>
                    <option value="NormalActivity"><fmt:message key="NormalActivity"/></option>
                    <option value="HighActivity"><fmt:message key="HighActivity"/></option>
                    <option value="VeryHighActivity"><fmt:message key="VeryHighActivity"/></option>
                </select>
            </label>
        <label class="col-lg-12"><input type="submit" value="<fmt:message key="register"/>"></label>
    </form>
</w:wrapper>
