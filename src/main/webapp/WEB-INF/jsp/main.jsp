<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<w:wrapper>
<h1 class="col-lg-12"><fmt:message key="main"/></h1>
    <div class="col-lg-6 text-center main_info border-bottom">
        <img src="../../img/img1.png" alt="img1"><br>
        <img src="../../img/img2.png" alt="img2">
    </div>
    <div class="col-lg-6 text-left main_info border-bottom">
        <h3><fmt:message key="main.info.1"/></h3>
    </div>
    <div class="col-lg-6 text-center main_info border-bottom">
        <img src="../../img/img3.png" alt="img3"><br>
    </div>
    <div class="col-lg-6 text-left main_info border-bottom">
        <h3><fmt:message key="main.info.2"/></h3>
    </div>
    <div class="col-lg-6 text-center main_info ">
        <img src="../../img/img4.png" alt="img4"><br>
        <img src="../../img/img5.png" alt="img5">
    </div>
    <div class="col-lg-6 text-left main_info">
        <h3><fmt:message key="main.info.3"/> </h3>
    </div>
</w:wrapper>
