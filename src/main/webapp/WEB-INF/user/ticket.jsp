<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hello" uri="/WEB-INF/lib/hello.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Railway System</title>

    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/bootstrap-theme.css"/>"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/style.css"/>"/>
    <script src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

    <fmt:setBundle basename="ticketsPage" var="ticketsPage"/>
    <fmt:setBundle basename="navbar" var="navbar"/>
    <fmt:setBundle basename="message" var="message"/>
</head>
<body style="background: #fff">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Railway System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/RailwaySystem?command=main"><fmt:message key="navbar.main" bundle="${navbar}"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="navbar-brand"><hello:user name="${fn:escapeXml(username)}"/></a></li>
            <li><a href="/RailwaySystem?command=EN">EN</a></li>
            <li><a href="/RailwaySystem?command=UKR">UKR</a></li>
            <li><a href="/RailwaySystem?command=logout"><fmt:message key="navbar.logout" bundle="${navbar}"/></a></li>
        </ul>
    </div>
</nav>

<div class="col-md-2"></div>
<div class="col-md-8">
    <h1 style="text-align: center"><fmt:message key="tickets.remember" bundle="${ticketsPage}"/></h1>
    <table class="table">
        <tr>
            <th><fmt:message key="tickets.application" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.trainNumber" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.name" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.surname" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.departure" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.arrival" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.from" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.to" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.type" bundle="${ticketsPage}"/></th>
            <th><fmt:message key="tickets.price" bundle="${ticketsPage}"/></th>
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
        </tr>
        </c:forEach>
        </tr>
        <c:remove var="tickets"/>
    </table>
</div>
<div class="col-md-2"></div>
</body>
</html>
