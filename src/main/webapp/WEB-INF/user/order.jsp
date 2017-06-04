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
          href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css"/>"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
    <script src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.js"/> "></script>
    <script src="<c:url value="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"/> "></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Railway System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="?command=Main">Main</a></li>
            <li class="right"><a href="?command=Log out">Log out</a></li>
        </ul>
    </div>
</nav>

<div class="col-md-2"></div>
<div class="col-md-8">
    <c:if test="${not empty noTickets}">
        No selected tickets. Please, go back and choose these ones
    </c:if>
    <c:if test="${not empty tickets}">
        <form action="/make" method="post">
            <table class="table">
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
                    <td>${ticket.trainId}</td>
                    <td>${ticket.name}</td>
                    <td>${ticket.surname}</td>
                    <td>${ticket.fromDate}</td>
                    <td>${ticket.toDate}</td>
                    <td>${ticket.fromCity}</td>
                    <td>${ticket.toCity}</td>
                    <td>${ticket.typePlace} (${ticket.max})</td>
                    <td>${ticket.price}</td>
                    <td style="width: 80px">x<input type="number" name="${ticket.trainId}" max="${ticket.max}" min="0"
                                                    value="1"></td>
                </tr>
                </c:forEach>
                </tr>
            </table>
            <input type="hidden" name="tickets" value="${tickets}">
            <input type="submit" name="command" value="Book Tickets">
        </form>
    </c:if>
</div>
<div class="col-md-2"></div>
</body>
</html>
