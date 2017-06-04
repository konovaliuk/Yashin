<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>

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
    <fmt:setLocale value="${javax.servlet.jsp.jstl.fmt.locale.request}"/>
    <fmt:setBundle basename="login" var="login"/>
    <fmt:setBundle basename="command" var="command"/>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">--%>
</head>
<body>
<div class="row">
    <div class="col-md-5"></div>
    <div style="padding-top: 20%" class="col-md-2 center-block">
        <form method="post" action="/route" class="myform"/>
        <div class="form-group">
            <label for="email"><fmt:message key="login.email" bundle="${login}"/></label><br>
            <input type="text" placeholder="<fmt:message key="login.placeholderEmail" bundle="${login}"/>" name="email"
                   id="email"
                   required/>
        </div>
        <div class="form-group">
            <label for="pwd"><fmt:message key="login.password" bundle="${login}"/></label><br>
            <input type="password" placeholder="<fmt:message key="login.placeholderPassword" bundle="${login}"/>"
                   name="password" id="pwd"
                   required/>
        </div>
        <input type="submit" value="<fmt:message key="command.entry" bundle="${command}"/>" name="command">
        </br>
        <c:if test="${requestScope.errorMessage != null}">
            <c:out value="${requestScope.errorMessage}"/>
        </c:if>
        </form>
        <p class="message"><fmt:message key="login.notregistered" bundle="${login}"/>
            <a href="<c:url value="${pageContext.request.contextPath}/resources/register.jsp"/>"><fmt:message
                    key="login.signUp" bundle="${login}"/></a></p>
    </div>
    <div class="col-md-5"></div>
</div>


<footer>
    <form action="/login" method="get">
        <button class="" type="submit" name="command" value="EN"><fmt:message key="command.en"
                                                                              bundle="${command}"/></button>
        <button type="submit" name="command" value="UKR"><fmt:message key="command.ukr" bundle="${command}"/></button>
    </form>
</footer>


</body>
</html>
