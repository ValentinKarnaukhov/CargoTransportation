<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Trucks"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="trucks" value="active"/>
</jsp:include>

<h5 class="indigo-text" style="margin-left: 1%">Truck list</h5>
<table class="centered highlight card-panel">
    <thead>
    <tr>
        <th>Registration number</th>
        <th>Max drivers</th>
        <th>Capacity</th>
        <th>Status</th>
        <th>City</th>
        <th>Order</th>
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
            <td>${truck.order.order_id}</td>
            <td><a href="<c:url value='/manager_/edit_truck_${truck.truck_id}'/>">
                <i class="material-icons">edit</i></a></td>
            <td><a href="<c:url value='/manager_/delete_truck_${truck.truck_id}'/>">
                <i class="material-icons">delete_forever</i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div ><a class="waves-effect waves-light btn" href="<c:url value='/manager_/trucks/newtruck' />">Add new truck
    <i class="material-icons left">local_shipping</i></a></div>

<jsp:include page="../footer.jsp"/>
