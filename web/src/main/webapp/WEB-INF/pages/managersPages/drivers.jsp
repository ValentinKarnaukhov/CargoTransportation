<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Drivers"/>
</jsp:include>

<s:message code="manager.drivers.label" var="label"/>

<jsp:include page="../leftMenu.jsp">
        <jsp:param name="drivers" value="active"/>
        <jsp:param name="label" value="${label}"/>
</jsp:include>

    <table class="centered highlight bordered card-panel table">
        <thead>
        <tr>
            <th><s:message code="manager.drivers.table.personalcode"/></th>
            <th><s:message code="manager.drivers.table.firstname"/></th>
            <th><s:message code="manager.drivers.table.secondname"/></th>
            <th><s:message code="manager.drivers.table.email"/></th>
            <th><s:message code="manager.drivers.table.city"/></th>
            <th><s:message code="manager.drivers.table.status"/></th>
            <th><s:message code="manager.drivers.table.order"/></th>
            <th><s:message code="manager.drivers.table.time"/></th>
            <th width="50"></th>
            <th width="50"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <td >${driver.personal_code}</td>
                <td >${driver.first_name}</td>
                <td >${driver.last_name}</td>
                <td >${driver.user.email}</td>
                <td >${driver.city.name}</td>
                <td>${driver.status}</td>
                <td>${driver.truck.order.order_id}</td>
                <td>${driver.worked_time}</td>
                <td><a href="<c:url value='/manager_/edit_driver_${driver.driver_id}'/>">
                    <i class="material-icons blue-grey-text">edit</i></a></td>
                <td><a href="<c:url value='/manager_/delete_driver_${driver.driver_id}'/>">
                    <i class="material-icons blue-grey-text">delete_forever</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<a class="waves-effect waves-light btn blue-grey" href="<c:url value='/manager_/drivers/newdriver' />"><s:message code="manager.drivers.button.add"/>
    <i class="material-icons left">person_add</i></a>
<a style="margin-left: 30px" class="waves-effect waves-light btn blue-grey" href="<c:url value='/manager_/drivers/updateTime' />"><s:message code="manager.drivers.button.salary"/>
    <i class="material-icons left">attach_money</i></a>
<jsp:include page="../footer.jsp"/>