<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.10.2021
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Managers Homepage</title>
</head>
<body>

<h2>Welcome to Managers homepage</h2>

<p>
    My Managers note:  You win or you learn !
</p>

<a href="${pageContext.request.contextPath}/">Back to Home Page</a>

</body>
</html>
