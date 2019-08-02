<%@tag description="wrapper" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <jsp:include page="../jsp/parts/header.jsp"/>
</c:if>
<c:if test="${sessionScope.user != null}">
    <jsp:include page="../jsp/parts/headerForLogged.jsp"/>
</c:if>
<div class="container wrapp">
    <div class="row text-center">
        <jsp:doBody/>
    </div>
</div>
<jsp:include page="../jsp/parts/footer.jsp"/>
</body>
</html>