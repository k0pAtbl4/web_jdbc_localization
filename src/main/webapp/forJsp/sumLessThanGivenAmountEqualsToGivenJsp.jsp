<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="sum.title"/></title>
</head>
<body>
<form method="post">
    <label><fmt:message key="sum.sum"/>:
        <input type="text" name="sum"><br/>
    </label>
    <label><fmt:message key="sum.amount"/>:
        <input type="text" name="amount"><br/>
    </label>
    <button type="submit"><fmt:message key="submit"/></button>
</form>
</body>
</html>
