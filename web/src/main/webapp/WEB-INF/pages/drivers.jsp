<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Drivers</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
</head>
<body>
<div class="panel-heading"><span class="lead">List of Users </span></div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>SSO ID</th>
        <th width="100"></th>
        <th width="100"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.ssoId}</td>
            <td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success

custom-width">edit</a></td>
            <td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger

custom-width">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<div class="well">
    <a href="<c:url value='/newuser' />">Add New User</a>
</body>
</html>
