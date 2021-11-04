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
    <title>My Homepage</title>
</head>
<body>

<h2>Welcome to theHomePage</h2>

<p>
    User: <security:authentication property="principal.username"/>
    <br><br>
    Role(s): <security:authentication property="principal.authorities"/>

</p>

<br>

<security:authorize access="hasRole('MANAGER')">
    <br>
    <a href="${pageContext.request.contextPath}/managers">Managers meeting (only for managers)</a>
    <br>
</security:authorize>


<br>
<security:authorize access="hasRole('ADMIN')">
    <a href="${pageContext.request.contextPath}/admins">Admins dungeon (only for admins)</a>
</security:authorize>
<br><br><br><br><br><br>
<h></h>


<form:form action="${pageContext.request.contextPath}/logout" method="post">

    <input type="submit" value="Logout"/>

</form:form>

</body>
</html>
