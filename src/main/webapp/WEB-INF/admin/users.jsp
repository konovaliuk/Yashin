<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li class="active"><a href="?command=Users">Users</a></li>
            <li><a href="?command=Tickets">Tickets</a></li>
            <li class="right"><a href="?command=Log out">Log out</a></li>
        </ul>
    </div>
</nav>
<div class="col-md-2"></div>
<div class="col-md-8">

    <form method="post" action="/admin">
        <table class="table">
            <tr>
                <th>Email</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Telephone</th>
                <th>Admin</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.phone}</td>
                    <td>${user.admin}</td>
                    <td>
                        <select name="${user.id}" class="form-control">
                            <option value="none"></option>
                            <option value="delete">Delete</option>
                            <option value="admin">Make an Admin</option>
                            <option value="user">Make a User</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: right; padding: 15px">
            <input type="submit" name="command" value="Done" class="btn btn-primary btn-md">
        </div>
    </form>
</div>
<div class="col-md-2"></div>
</body>
</html>
