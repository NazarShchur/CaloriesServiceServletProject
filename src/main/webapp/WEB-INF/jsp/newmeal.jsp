<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<w:wrapper>
    <h1 class="col-lg-12"><fmt:message key="new.meal"/></h1>
    <h2 class="col-lg-12"><fmt:message key="new.meal.text"/></h2>
    <div class="col-lg-12">
        <c:if test="${param.noFoodAdded == true}">
            <h3 class="col-lg-12"><fmt:message key="add.food.first"/></h3>
        </c:if>
        <form class="add_food_to_meal" action="${pageContext.request.contextPath}/app/userpage/newmeal/addfoodtomeal" method="post">
            <select name="foodID" required>
                <option selected value="" disabled><fmt:message key="select.food"/></option>
                <c:forEach items="${sessionScope.availableFood}" var="food">
                    <option value="${food.id}">${food.name}</option>
                </c:forEach>
            </select>
            <input name="count" type="number" required><br>
            <input type="submit" class="custom_submit" value="<fmt:message key="add"/>">
        </form>
    </div>
    <div class="col-lg-12">
        <c:if test="${sessionScope.isFoodInMap == true}">
            <form action="${pageContext.request.contextPath}/app/userpage/newmeal/deletefoodfrommeal" method="post">
                <table class="table-bordered mealtable">
                    <tr>
                        <td><fmt:message key="food.name"/></td>
                        <td><fmt:message key="food.count"/></td>
                        <td><fmt:message key="food.action"/></td>
                    </tr>
                    <c:forEach items="${sessionScope.currentMap.map}" var="entry">
                        <tr>
                            <td>
                                <c:out value="${entry.key.name}"/>
                            </td>
                            <td>
                                <c:out value="${entry.value}"/>
                            </td>
                            <td>
                                <button class="custom_submit" type="submit" name="foodIDToDelete" value="<c:out value="${entry.key.id}"/>">
                                    <fmt:message key="delete.food.from.meal"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><fmt:message key="total.calories"/></td>
                        <td><c:out value="${sessionScope.currentMap.calories}"/></td>
                    </tr>
                </table>
            </form>
            <form class="col-lg-12" action="${pageContext.request.contextPath}/app/userpage/newmeal/savemeal" method="post">
                <label>Description <input type="text" maxlength="50" name="description"></label>
                <input type="submit" class="custom_submit" value="<fmt:message key="add.meal"/>">
            </form>
        </c:if>
    </div>

</w:wrapper>
