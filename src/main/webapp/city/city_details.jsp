<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły miasta</title>
</head>
<body>
    <H1>${requestScope.city.name}</H1>
    <p>kod kraju: ${city.countryCode}</p>
    <p>szerokość geograficzna: ${requestScope.city.latitude} </p>
    <p>długość geograficzna: ${requestScope.city.longitude}</p>
    <p>populacja: ${requestScope.city.population}</p>
    <p>strefa czasowa: ${requestScope.city.timeZone.displayName}</p>
    <a href="one?id=${param.id - 1}">Previous</a>
    <a href="one?id=${param.id + 1}">Next</a>
</body>
</html>
