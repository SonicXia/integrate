<%--
  Created by IntelliJ IDEA.
  User: Sonic
  Date: 2019/3/25
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

    <form action="/shiro/loginUser" method="post">
        <input type="text" name="username"> <br>
        <input type="password" name="password"> <br>
        <input type="submit" value="提交">
    </form>

</body>
</html>
