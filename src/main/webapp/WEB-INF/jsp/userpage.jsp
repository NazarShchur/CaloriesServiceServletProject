<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>

<w:wrapper>
    <div class="col-lg-12">
        <h1 class="uname"> <c:out value="${sessionScope.user.login}"/></h1>
        <c:if test="${sessionScope.isAdmin == true}">
            <h1>
                <a href="${pageContext.request.contextPath}/app/admin">
                    <fmt:message key="admin.page"/>
                </a>
            </h1>
        </c:if>
    </div>
    <div class="col-lg-5 uphoto">

    </div>
    <div class="col-lg-7 ustats">
        <ul>
            <li>
                <fmt:message key="personal.data"/>
            </li>
            <li>
                <fmt:message key="age"/> : <c:out value="${sessionScope.user.age}"/>
            </li>
            <li>
                <fmt:message key="gender"/> : <fmt:message key="${sessionScope.user.gender}"/>
            </li>
            <li>
                <fmt:message key="weight"/> : <c:out value="${sessionScope.user.weight}"/>
            </li>
            <li>
                <fmt:message key="height"/> : <c:out value="${sessionScope.user.height}"/>
            </li>
            <li>
                <fmt:message key="lifestyle"/> : <fmt:message key="${sessionScope.user.lifeStyle}"/>
            </li>
            <li>
                <fmt:message key="daily.calories"/> : <c:out value="${sessionScope.user.dailyCalories}"/>
            </li>
            <li>
                <fmt:message key="today.consumed"/> : <c:out value="${sessionScope.todayEaten}"/>
                <c:if test="${sessionScope.isNormExceeded == true}">
                    !
                </c:if>
            </li>
        </ul>
    </div>
    <div class="profile_act col-lg-12 row">
        <a href="${pageContext.request.contextPath}/app/userpage/newmeal" class="col-lg-4 profile_btn">
            <fmt:message key="add.meal"/>
        </a>
        <a href="${pageContext.request.contextPath}/app/userpage/addfood" class="col-lg-4 profile_btn">
            <fmt:message key="add.food"/>
        </a>
        <a href="${pageContext.request.contextPath}/app/userpage/allmeals" class="col-lg-4 profile_btn">
            <fmt:message key="all.meal"/>
        </a>
    </div>
</w:wrapper>

