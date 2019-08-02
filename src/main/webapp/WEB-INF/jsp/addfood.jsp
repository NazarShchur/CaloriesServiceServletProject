<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<w:wrapper>
<h1 class="col-lg-12 text-center">
    <fmt:message key="add.food"/>
</h1>
    <c:if test="${param.unacceptableData == true}">
        <p><fmt:message key="unacceptableData"/></p>
    </c:if>
    <form class="col-lg-12" method="post" action="${pageContext.request.contextPath}/app/userpage/addfood/savefood">
       <label>
           <input type="text" maxlength="30" placeholder="<fmt:message key="food.name"/>" name="foodName" required>
       </label>
        <label>
            <input type="number" placeholder="<fmt:message key="food.proteins"/>" name="protein" required>
        </label>
        <label>
            <input type="number" placeholder="<fmt:message key="food.fats"/>" name="fats" required>
        </label>
        <label>
            <input type="number" placeholder="<fmt:message key="food.carbohydrates"/>" name="carbohydrate" required>
        </label>
        <label>
            <input class="custom_submit" type="submit" value="<fmt:message key="add"/>">
        </label>
    </form>
</w:wrapper>
