<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  <link rel="stylesheet" href=<c:url value="/WEB-INF/css/login.css"/> type="text/css">
</head>

<body>
<div class="login-page">
  <div class="form">
    <form class="login-form" method="post" action="controller">
      <input type="text" placeholder="email" name="email"/>
      <input type="password" placeholder="password" name="password"/>
      <input type="submit" name="entry"/>
      <p class="message">Not registered? <a href="#">Create an account</a></p>
    </form>
  </div>
</div>
</body>
</html>
