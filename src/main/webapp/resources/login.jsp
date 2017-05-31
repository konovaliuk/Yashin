<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <link rel="stylesheet"
        type="text/css"
        href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
</head>

<body>
<div class="page">
  <div class="form">
    <form class="login-form" method="post" action="/route"/>
      <input type="text" placeholder="email" name="email" required/>
      </br>
      <input type="password" placeholder="password" name="password" required/>
      </br>
      <input type="submit" value="Sign in" name="command">
      </br>
      <c:if test="${requestScope.errorMessage != null}">
        <c:out value="${requestScope.errorMessage}"/>
      </c:if>
    </form>
    <p class="message">Not registered?
      <a href="<c:url value="${pageContext.request.contextPath}/resources/register.jsp"/>">Sign up</a></p>
  </div>
</div>
</body>
</html>
