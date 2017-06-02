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
<body>
<form action="/logout" method="post" class="logout">
    <input type="submit" name="command" value="Log out">
</form>

<c:if test="${not empty noTickets}">
    No selected tickets. Please, go back and choose these ones
</c:if>
<c:if test="${not empty tickets}">
    <form action="/make" method="get" >
        <table class="simple-little-table">
            <tr>
                <th>Train Number</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Arrival</th>
                <th>Departure</th>
                <th>From</th>
                <th>To</th>
                <th>Type of Place</th>
                <th>Price</th>
                <th>Count</th>
            </tr>
            <tr>
                <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td>${ticket.train_id}</td>
                <td>${ticket.name}</td>
                <td>${ticket.surname}</td>
                <td>${ticket.fromDate}</td>
                <td>${ticket.toDate}</td>
                <td>${ticket.fromCity}</td>
                <td>${ticket.toCity}</td>
                <td>${ticket.typePlace} (${ticket.max})</td>
                <td>${ticket.price}</td>
                <td style="width: 80px">x<input type="number" name="${ticket.train_id}" max="${ticket.max}" min="0" value="1"></td>
            </tr>
            </c:forEach>
            </tr>
        </table>
        <input type="hidden" name="tickets" value="${tickets}">
        <input type="submit" name="command" value="Book Tickets">
    </form>
</c:if>
</body>
</html>
