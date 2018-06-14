<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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

<h5 class="indigo-text table-in"><s:message code="manager.truck.label"/></h5>
<table class="centered bordered highlight card-panel table">
    <thead>
    <tr>
        <th><s:message code="manager.truck.table.regnumber"/></th>
        <th><s:message code="manager.truck.table.max"/></th>
        <th><s:message code="manager.truck.table.capacity"/></th>
        <th><s:message code="manager.truck.table.status"/></th>
        <th><s:message code="manager.truck.table.city"/></th>
        <th><s:message code="manager.truck.table.order"/></th>
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

<div ><a class="waves-effect waves-light btn blue-grey" href="<c:url value='/manager_/trucks/newtruck' />"><s:message code="manager.truck.button.add"/>
    <i class="material-icons left ">local_shipping</i></a></div>

<jsp:include page="../footer.jsp"/>
