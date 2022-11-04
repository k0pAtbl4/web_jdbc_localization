<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<c:if test="${errorMessage eq true}">
    <p id="message">
        <fmt:message key="login.errorMessage"/>
    </p>
</c:if>
</body>
</html>
