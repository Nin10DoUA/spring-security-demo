<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.10.2021
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Custom Login Page</title>
</head>
<body>

<h3>My Custom Login Page</h3>

<c:if test="${param.error != null}">

    <span style="color: red">Sorry! You entered wrong login/password</span>

</c:if>

<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
    <p>
        User name: <input type="text" name="username"/>
    </p>
    <p>
        Password: <input type="text" name="password"/>
    </p>
    <input type="submit" value="Login"/>
</form:form>


</body>
</html>
