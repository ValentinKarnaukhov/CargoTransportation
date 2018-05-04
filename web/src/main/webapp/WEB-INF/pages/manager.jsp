<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 03.05.2018
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
</head>
<body>
Dear, <strong>${user}</strong>, it's manger page. Welcome!<br>
<a class="btn btn-block btn-primary btn-default" href="<c:url value="/manager_/drivers"/>">Drivers</a>
<a class="btn btn-block btn-primary btn-default" href="<c:url value="/manager_/orders"/>">Orders</a>
<a class="btn btn-block btn-primary btn-default" href="<c:url value="/manager_/trucks"/>">Trucks</a>
</body>
</html>
