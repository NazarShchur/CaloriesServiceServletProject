<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>

<w:wrapper>
    <style>
        td{
            padding: 10px;
        }
    </style>
    <h1>
        <fmt:message key="food.added.by.users"/>
    </h1>
    <table class="table-bordered">
        <tr>
            <td><fmt:message key="food.name"/></td>
            <td><fmt:message key="food.proteins"/></td>
            <td><fmt:message key="food.fats"/></td>
            <td><fmt:message key="food.carbohydrates"/></td>
            <td><fmt:message key="food.user.id"/></td>
            <td><fmt:message key="food.action"/></td>

        </tr>
        <c:forEach items="${sessionScope.userFood}" var="food">
            <tr>
                <td>${food.name}</td>
                <td>${food.protein}</td>
                <td>${food.fats}</td>
                <td>${food.carbohydrate}</td>
                <td>${food.userID}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/app/admin/makefoodpublic" method="post">
                        <button type="submit" name="foodID" value="${food.id}">
                            <fmt:message key="food.make.public"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</w:wrapper>