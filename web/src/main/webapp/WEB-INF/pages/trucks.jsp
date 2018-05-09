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
    <title>Trucks</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
</head>
<body>
<div class="panel-heading"><span class="lead">List of drivers </span></div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Registration number</th>
        <th>Max drivers</th>
        <th>Capacity</th>
        <th>Status</th>
        <th>City</th>
        <th width="100"></th>
        <th width="100"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${trucks}" var="truck">
        <tr>
            <td>${truck.reg_number}</td>
            <td>${truck.max_drivers}</td>
            <td>${truck.capacity}</td>
            <td>${truck.status}</td>
            <td>${truck.city.name}</td>
            <td><a href="<c:url value='/manager_/edit_truck_${truck.truck_id}'/>"
                   class="btn btn-success custom-width">edit</a></td>
            <td><a href="<c:url value='/manager_/delete_truck_${truck.truck_id}'/>"
                   class="btn btn-danger custom-width">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="well"><a href="<c:url value='/manager_/trucks/newtruck' />">Add new truck</a></div>
</body>
</html>
