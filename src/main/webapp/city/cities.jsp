<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 06.10.2019
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
</head>
<body>
    ${pageContext.request.queryString}
    ${pageContext.request.contextPath}
    <title>city</title>
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
    <%--
    <a href="?page=1">pierwsza</a>
    <c:if test="${requestScope.prev != null}">
        <a href="?page=${requestScope.prev}">poprzednia</a>
    </c:if>

    <c:if test="${requestScope.next != null}">
        <a href="?page=${requestScope.next}">następna</a>
    </c:if>
    <c:if test="${requestScope.next == null}">
        <span> następna</span>
    </c:if>
    <a href="?page=${requestScope.last}">ostatnia</a>
    --%>
    <a href="?${fn:replace(
         pageContext.request.queryString,
         requestScope.current,
         "page=1"
         )}">pierwsza</a>
    <c:if test="${requestScope.prev != null}">
        <a href="?${fn:replace(
         pageContext.request.queryString,
         requestScope.current,
         requestScope.prev
         )}">poprzednia</a>
    </c:if>
    <c:if test="${requestScope.next != null}">
        <a href="?${fn:replace(
         pageContext.request.queryString,
         requestScope.current,
         requestScope.next
         )}">następna</a>
    </c:if>
    <a href="?${fn:replace(
         pageContext.request.queryString,
         requestScope.current,
         requestScope.last
         )}">ostatnia</a>
</body>
</html>
