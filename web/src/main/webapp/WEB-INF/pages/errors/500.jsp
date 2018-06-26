<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 26.06.2018
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/min/materialize.min.css"/>"/>
</head>
<body>
<div class="center">
    <img src="<c:url value="/static/img/500.jpg"/>" style="margin-top: 5%"/><br>
    <span style="font-size: 50px">500 INTERNAL SERVER ERROR</span><br>
    <a href="<c:url value="/login"/>">Home</a>
</div>
</body>
</html>
