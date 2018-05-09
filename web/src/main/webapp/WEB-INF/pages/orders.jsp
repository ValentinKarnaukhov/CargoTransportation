<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
</head>
<body>
<div class="panel-heading"><span class="lead">List of drivers </span></div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Number</th>
        <th>Truck</th>
        <th>Is Completed</th>
        <th width="100"></th>
        <th width="100"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.order_id}</td>
            <td>${order.truck.reg_number}</td>
            <td>${order.complete}</td>
            <td><a href="<c:url value='/manager_/orders/info_${order.order_id}'/>"
                   class="btn btn-success custom-width">info</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="well"><a href="<c:url value='/manager_/orders/neworder' />">Add new order</a></div>
</body>
</html>
