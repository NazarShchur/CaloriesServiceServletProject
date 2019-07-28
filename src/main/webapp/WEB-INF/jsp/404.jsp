<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib prefix="w" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<w:wrapper>
    <div class="err">
        <p class="err_code">404</p>
        <p class="err_mess">NOT FOUND</p>
    </div>
</w:wrapper>
