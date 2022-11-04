<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<form action="login">
    <fmt:message key="login.name"/>:<input type="text" name="name"/><br/>
    <fmt:message key="login.password"/>:<input type="password" name="password"/><br/>

    <input type="submit" value="<fmt:message key="submit"/>">

</form>
</body>
</html>
