<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Admin Console</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
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
            <li><a href="?command=Users">Users</a></li>
            <li class="active"><a href="?command=Tickets">Tickets</a></li>
            <li class="right"><a href="?command=Log out">Log out</a></li>
        </ul>
    </div>
</nav>
<div class="col-md-1"></div>
<div class="col-md-10">
    <form action="/cancel" method="post">
        <input type="submit" name="command" value="Cancel">
        <input type="submit" name="command" value="Cancel All">
        <table class="table">
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
                <th>Cancel?</th>
            </tr>
            <tr>
                <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td>#${ticket.requestId}</td>
                <td>${ticket.trainId}</td>
                <td>${ticket.name}</td>
                <td>${ticket.surname}</td>
                <td>${ticket.fromDate}</td>
                <td>${ticket.toDate}</td>
                <td>${ticket.fromCity}</td>
                <td>${ticket.toCity}</td>
                <td>${ticket.typePlace}</td>
                <td>${ticket.price}</td>
                <td><input type="checkbox" name="${ticket.requestId}" value="cancel"></td>
            </tr>
            </c:forEach>
            </tr>
        </table>
    </form>

</div>
<div class="col-md-1"></div>
</body>
</html>
