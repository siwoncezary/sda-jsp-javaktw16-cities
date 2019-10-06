<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <H1>Podaj datę urodzin</H1>
    <form action="${requestScope.contextPath}" method="post">
        <input type="date" name="birthDate">
        <button type="submit">Oblicz</button>
    </form>
</body>
</html>
