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

<h5 class="indigo-text table-in">TRUCKS LIST</h5>
<table class="centered bordered highlight card-panel table">
    <thead>
    <tr>
        <th>REGISTRATION NUMBER</th>
        <th>MAX DRIVERS</th>
        <th>CAPACITY</th>
        <th>STATUS</th>
        <th>CITY</th>
        <th>ORDER</th>
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
                <i class="material-icons blue-grey-text">edit</i></a></td>
            <td><a href="<c:url value='/manager_/delete_truck_${truck.truck_id}'/>">
                <i class="material-icons blue-grey-text">delete_forever</i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div ><a class="waves-effect waves-light btn blue-grey" href="<c:url value='/manager_/trucks/newtruck' />">Add new truck
    <i class="material-icons left ">local_shipping</i></a></div>

<jsp:include page="../footer.jsp"/>
