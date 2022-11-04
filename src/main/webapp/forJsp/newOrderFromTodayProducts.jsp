<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="newOrder.title"/></title>
</head>
<body>
<form method="post">
    <button type="submit"><fmt:message key="submit"/></button>
</form>
</body>
</html>
