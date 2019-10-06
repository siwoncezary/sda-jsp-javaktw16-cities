<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>

    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
    ${pageContext.request.queryString}
    <table>
        <tr>
            <th>
               id
            </th>
            <th>
                name
            </th>
            <th>
                country
            </th>
            <th>
                time zone
            </th>
        </tr>
        <c:forEach items="${requestScope.cities}" var="city" varStatus="status">
            <c:if test="${status.index % 2 == 0}">
                <tr style="color: aqua">
            </c:if>
            <c:if test="${status.index %2 != 0}">
                <tr style="color: blue">
            </c:if>
            <c:if test="${status.last}">
                <tr style="color: red">
            </c:if>
                <td>
                    ${city.id}
                </td>
                <td>
                    ${city.name}
                </td>
                <td>
                    ${city.countryCode}
                </td>
                <td>
                    ${city.timeZone.displayName}
                </td>
            </tr>
        </c:forEach>
    </table>
        <a href="?page=0">FIRST</a>
    <c:if test="${(requestScope.current-1) >= 0}">
        <a href="?page=${requestScope.current-1 > requestScope.last ? requestScope.last : requestScope.current-1}">PREVIOUS</a>
    </c:if>
    <c:if test="${requestScope.current < requestScope.last}">
        <a href="?page=${requestScope.current + 1}">NEXT</a>
    </c:if>
    <a href="?page=${requestScope.last}">LAST</a>
</body>
</html>
