<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find city</title>
</head>
<body>
<a href = ${pageContext.request.contextPath}/index.jsp>HOME</a>
    <form action="?find" method="get">
        <select name="countryCode">
            <c:forEach items="${requestScope.codes}" var="code">
                <option value="${code}">${code}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="page" value="1">
        <button type="submit">Znajdź</button>
    </form>
</body>
</html>
