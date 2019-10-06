<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 09:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:useBean id="date" class="java.util.Date"/>
    ${date.toString()}
    <jsp:useBean id="user" class="entity.UserBean"/>
    <jsp:setProperty name="user" property="name" value="KAZIK"/>
    <jsp:setProperty name="user" property="birthDate" value="2000-10-12"/>
    <p>Liczba dni przeżyta:
        <jsp:getProperty name="user" property="lifeDays"/> <br>
    prze użytkownika <%=user%>
    </p>

</body>
</html>
