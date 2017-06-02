<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Console</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
</head>
<body>
<form action="/logout" method="post" class="logout">
    <input type="submit" name="command" value="Log out">
</form>
<div class="admin">
    <form method="post" action="/admin">
        <table class="simple-little-table">
            <tr>
                <th>Email</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Telephone</th>
                <th>Admin</th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.phone}</td>
                    <td>${user.admin}</td>
                    <td>
                        <select name="${user.id}">
                            <option value="none"></option>
                            <option value="delete">Delete</option>
                            <option value="admin">Make an Admin</option>
                            <option value="user">Make a User</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input class="forminput" type="submit" name="command" value="Done">
    </form>
</div>
</div>
</body>
</html>
