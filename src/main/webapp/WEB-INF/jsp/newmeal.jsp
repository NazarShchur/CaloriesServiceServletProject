<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<w:wrapper>
    <h1 class="col-lg-12"><fmt:message key="new.meal"/></h1>
    <div class="col-lg-12">
        <c:if test="${requestScope.noFoodAdded == true}">
            <h1 class="col-lg-12">ADD FOOD FIRST</h1>
        </c:if>
    <form action="${pageContext.request.contextPath}/app/userpage/newmeal/addfoodtomeal" method="post">
        <select name="foodID">
            <option selected disabled><fmt:message key="select.food"/></option>
            <c:forEach items="${sessionScope.availableFood}" var="food">
                <option value="${food.id}">${food.name}</option>
            </c:forEach>
        </select>
        <label><input name="count" type="number">gramm</label>
        <input type="submit">
    </form>
    </div>
    <div class="col-lg-12">
    <c:if test="${sessionScope.isFoodInMap == true}">
        <form action="${pageContext.request.contextPath}/app/userpage/newmeal/deletefoodfrommeal" method="post">
            <c:forEach items="${sessionScope.currentMap.map}" var="entry">
                <p>
                    <label>
                        <c:out value="${entry.key.name}"/> - <c:out value="${entry.value}"/>
                        <button type="submit" name="foodIDToDelete" value="<c:out value="${entry.key.id}"/>">
                            <fmt:message key="delete.food.from.meal"/>
                        </button>
                    </label>
                </p>
            </c:forEach>
        </form>
        <form action="${pageContext.request.contextPath}/app/userpage/newmeal/savemeal" method="post">
            <label>Description <input type="text" name="description"></label>
            <input type="submit" value="<fmt:message key="add.meal"/>">
        </form>
    </c:if>
    </div>

</w:wrapper>
