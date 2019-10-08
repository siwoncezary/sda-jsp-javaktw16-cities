<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Szczegóły miasta</title>
</head>
<body>
    <H1>${requestScope.city.name}</H1>
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, "id=0")}">FIRST</a>
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, requestScope.prev)}">PREVIOUS</a>
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, requestScope.next)}">NEXT</a>
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, requestScope.last)}">LAST</a>
    <p>kod kraju: ${city.countryCode}</p>
    <p>szerokość geograficzna: ${requestScope.city.latitude} </p>
    <p>długość geograficzna: ${requestScope.city.longitude}</p>
    <p>populacja: ${requestScope.city.population}</p>
    <p>strefa czasowa: ${requestScope.city.timeZone.displayName}</p>

    next ${requestScope.next}
    prev ${requestScope.prev}
    last ${requestScope.last}
    current ${requestScope.current}
</body>
</html>
