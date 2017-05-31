<%--
  Created by IntelliJ IDEA.
  User: andrew_yashin
  Date: 5/30/17
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Railway System</title>
</head>
<body>
    <table>
        <tr>
            <c:forEach items="${tickets}" var="ticket">
                <td>${ticket.train_id}</td>
                <td>${ticket.name}</td>
                <td>${ticket.surname}</td>
                <td>${ticket.fromDate}</td>
                <td>${ticket.toDate}</td>
                <td>${ticket.fromCity}</td>
                <td>${ticket.toCity}</td>
                <td>${ticket.typePlace}</td>
                <td>${ticket.price}</td>
            </c:forEach>
        </tr>
    </table>

</body>
</html>
