<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Orders"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>

<h5 class="indigo-text" style="margin-left: 1%">Orders list</h5>
<table class="centered striped card-panel">
    <thead>
    <tr>
        <th>Number</th>
        <th>Truck</th>
        <th>Is complete</th>
        <th width="50"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td >${order.order_id}</td>
            <td >${order.truck.reg_number}</td>
            <td >${order.complete}</td>

            <td><a href="<c:url value='/manager_/orders/order_info_${order.order_id}'/>">
                <i class="material-icons">info</i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div ><a class="waves-effect waves-light btn" href="<c:url value='/manager_/orders/neworder' />">Add new order
    <i class="material-icons left">add_box</i></a></div>
<c:if test="${param.created != null}">
    <div class="created"></div>
</c:if>
<jsp:include page="../footer.jsp"/>
