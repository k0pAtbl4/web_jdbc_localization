<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<div>
    <h1>Choose option:</h1>
</div>

<div>
    <div>
        <a href="login.html">Login</a>
        <button onclick="location.href='/web_jdbc_war_exploded/logout'">Logout</button>
    </div>
    <div>
        <button onclick="location.href='/web_jdbc_war_exploded/find-by-number'">Find by number</button>
        <button onclick="location.href='/web_jdbc_war_exploded/contain-product'">Orders that contain product</button>
        <button onclick="location.href='/web_jdbc_war_exploded/dont-contain-product'">Orders that dont contain products
            today
        </button>
        <button onclick="location.href='/web_jdbc_war_exploded/find-by-sum-amount'">Find by sum and amount</button>
        <button onclick="location.href='/web_jdbc_war_exploded/new-order'">New order from today products</button>
        <button onclick="location.href='/web_jdbc_war_exploded/delete-order'">Delete order</button>
    </div>
</div>
</body>
</html>
