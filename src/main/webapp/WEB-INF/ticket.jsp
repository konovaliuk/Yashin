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
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
</head>
<body style="background: #fff">
<table style="text-align: center">
    <tr>
        <th>Application</th>
        <th>Train Number</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Arrival</th>
        <th>Departure</th>
        <th>From</th>
        <th>To</th>
        <th>Type of Place</th>
        <th>Price (UAH)</th>
    </tr>
    <tr>
        <c:forEach items="${tickets}" var="ticket">
    <tr>
        <td>#${ticket.request_id}</td>
        <td>${ticket.train_id}</td>
        <td>${ticket.name}</td>
        <td>${ticket.surname}</td>
        <td>${ticket.fromDate}</td>
        <td>${ticket.toDate}</td>
        <td>${ticket.fromCity}</td>
        <td>${ticket.toCity}</td>
        <td>${ticket.typePlace}</td>
        <td>${ticket.price}</td>
    </tr>
    </c:forEach>
    </tr>
</table>
</body>
</html>
