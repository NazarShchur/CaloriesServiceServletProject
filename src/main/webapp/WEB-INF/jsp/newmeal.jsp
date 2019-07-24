<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<w:wrapper>
    <h1><fmt:message key="new.meal"/></h1>
    <form action="${pageContext.request.contextPath}/app/userpage/addmeal">
        <select name="foodName">
            <option selected disabled><fmt:message key="select.food"/> </option>
            <c:forEach items="${requestScope.foodSet}" var="food" begin="0" end="${requestScope.foodSetSize}">
                <option value="${food.name}">${food.name}</option>
            </c:forEach>
        </select>
        <label><input type="number">gramm</label>
        <input type="submit">
    </form>
</w:wrapper>
