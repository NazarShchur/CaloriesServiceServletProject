<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<w:wrapper>
    <style>
        td{
            padding: 10px;
        }
    </style>
    <h1 class="col-lg-12"><fmt:message key="all.meal"/></h1>
    <c:forEach items="${requestScope.mealsList}" var="meal">
        <div class="col-lg-12 align-content-center">
        <table class="table-bordered mealtable">
            <tr>
                <td>
                    <fmt:message key="meal.description"/>
                </td>
                <td>
                        ${meal.getDescription()} ${meal.getAddTime()}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="food.name"/>
                </td>
                <td>
                    <fmt:message key="food.fats"/>
                </td>
                <td>
                    <fmt:message key="food.carbohydrates"/>
                </td>
                <td>
                    <fmt:message key="food.proteins"/>
                </td>
                <td>
                    <fmt:message key="food.count"/>
                </td>
            </tr>
            <c:forEach items="${meal.foodMap}" var="entry">
            <tr>
                <td>
                        ${entry.key.name}
                </td>
                <td>
                        ${entry.key.fats}
                </td>
                <td>
                        ${entry.key.carbohydrate}
                </td>
                <td>
                        ${entry.key.protein}
                </td>
                <td>
                        ${entry.value}
                </td>
            </tr>
            </c:forEach>
        <tr>
            <td>
                <fmt:message key="total.calories"/>
            </td>
            <td>
                    ${meal.getAllCalories()}
            </td>
        </tr>
        </table>
        </div>
    </c:forEach>
    <ul class="pagin">
    <c:forEach var="page" begin="1" end="${requestScope.pageCount}">
            <li><a href="?page=${page}">${page}</a></li>
    </c:forEach>
    </ul>
</w:wrapper>