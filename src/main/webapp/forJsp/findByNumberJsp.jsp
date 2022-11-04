<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="findByNumber.title"/></title>
</head>
<body>
<form method="post">
    <label><fmt:message key="findByNumber.number"/>:
        <input type="text" name="number"><br/>
    </label>
    <button type="submit"><fmt:message key="submit"/></button>
</form>
</body>
</html>
