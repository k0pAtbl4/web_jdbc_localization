<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="ordersByProducts.title"/></title>
</head>
<body>
<form method="post">
    <label><fmt:message key="ordersByProducts.product"/>:
        <input type="text" name="product"><br/>
    </label>
    <button type="submit"><fmt:message key="submit"/></button>
</form>
</body>
</html>
