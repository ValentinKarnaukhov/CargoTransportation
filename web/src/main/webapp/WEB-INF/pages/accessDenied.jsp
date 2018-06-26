<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Access denied page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/min/materialize.min.css"/>"/>
</head>
<body>
<div class="center">
    <img src="<c:url value="/static/img/403.jpg"/>" style="margin-top: 5%;height: 20%"/><br>
    <span style="font-size: 50px">403 FORBIDDEN</span><br>
    <a href="<c:url value="/login"/>">Home</a>
</div>
</body>
</html>
