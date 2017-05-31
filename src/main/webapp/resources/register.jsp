<%--
  Created by IntelliJ IDEA.
  User: andrew_yashin
  Date: 5/29/17
  Time: 02:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
</head>
<body>
<div class="page">
    <div class="form">
        <form class="login-form" method="post" action="/login">
            <input type="email" placeholder="email" name="email" required/>
            </br>
            <input type="password" placeholder="password" minlength="8" name="password" required/>
            </br>
            <input type="text" placeholder="name" name="name" required/>
            </br>
            <input type="text" placeholder="surname" name="surname" required/>
            </br>
            <%--pattern="\([0-9]{3}\)\s[0-9]{3}-[0-9]{2}-[0-9]{2}"--%>
            <input type="tel" name="phone" placeholder="(050) 121-34-57" required/>
            </br>
            <c:if test="${requestScope.errorMessage != null}">
                <c:out value="${requestScope.errorMessage}"/>
            </c:if>
            </br>
            <input class="form" type="submit" value="Sign up" name="command"/>
        </form>
    </div>
</div>
</body>
</html>
