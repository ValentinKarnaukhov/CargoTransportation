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

<h5 class="indigo-text table-in">ORDERS LIST</h5>
<table class="centered bordered highlight card-panel table">
    <thead>
    <tr>
        <th>NUMBER</th>
        <th>TRUCK</th>
        <th>STATUS</th>
        <th width="50"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td >${order.order_id}</td>
            <td >${order.orderHistory.truck.reg_number}
                    <c:if test="${order.orderHistory.truck.status.name().equals('OK')}"><i class="material-icons green-text img">error</i></c:if>
                    <c:if test="${order.orderHistory.truck.status.name().equals('BROKEN')}"><i class="material-icons red-text img">error</i></c:if>
            </td>
            <td >${order.complete?"DONE":"IN PROGRESS"}</td>

            <td><a href="<c:url value='/manager_/orders/order_info_${order.order_id}'/>">
                <i class="material-icons blue-grey-text">info</i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div ><a class="waves-effect waves-light btn blue-grey" href="<c:url value='/manager_/orders/neworder' />">Add new order
    <i class="material-icons left">add_box</i></a></div>
<c:if test="${param.created != null}">
    <div class="created"></div>
</c:if>
<jsp:include page="../footer.jsp"/>
