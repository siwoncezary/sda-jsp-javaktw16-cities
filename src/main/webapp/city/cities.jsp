<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
</head>
<body>

    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>

    <p>Strona ${fn:replace(requestScope.current,'page=','')} z ${fn:replace(requestScope.last,'page=','')} </p>
    <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, "page=1")}">FIRST</a>
    <c:if test="${requestScope.prev != null}">
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, requestScope.prev)}">PREVIOUS</a>
    </c:if>
    <c:if test="${requestScope.next != null}">
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, requestScope.next)}">NEXT</a>
    </c:if>
    <c:if test="${requestScope.last != null}">
        <a href="?${fn:replace(pageContext.request.queryString, requestScope.current, requestScope.last)}">LAST</a>
    </c:if>
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

</body>
</html>
