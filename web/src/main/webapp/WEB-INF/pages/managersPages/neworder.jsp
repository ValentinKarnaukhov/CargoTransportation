<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 07.05.2018
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="New order"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>
<h5 class="indigo-text"><s:message code="manager.neworder.mainlabel"/></h5>
<div class="section"></div>
<div  class="row add-driver">

    <h5 class="indigo-text"><s:message code="manager.neworder.cargoes.label"/></h5>

        <table class="centered highlight bordered card-panel">
            <thead>
            <tr>
                <th><s:message code="manager.neworder.table.cargo"/></th>
                <th><s:message code="manager.neworder.table.weight"/></th>
                <th><s:message code="manager.neworder.table.city.loading"/></th>
                <th><s:message code="manager.neworder.table.city.unloading"/></th>
                <th width="50"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${waypoints}" var="waypoint">
                <tr>
                    <td>${waypoint.cargo.name}</td>
                    <td>${waypoint.cargo.weight}</td>
                    <td>${waypoint.loadingCity.name}</td>
                    <td>${waypoint.unloadingCity.name}</td>
                    <td><a href="<c:url value='/manager_/orders/delete_point_${waypoints.indexOf(waypoint)}'/>">
                        <i class="material-icons blue-grey-text">delete_forever</i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a class="waves-effect waves-light btn blue-grey" href="<c:url value='/manager_/orders/newcargo' />"><s:message code="manager.neworder.cargoes.button"/>
            <i class="material-icons left">add_box</i></a>

    <h5 style="margin-top: 100px" class="indigo-text"><s:message code="manager.neworder.truck.form.label"/></h5>
    <form:form class="card-panel" modelAttribute="order"  method="post" >
        <div class="input-field col s12">
            <form:select id ="truck" path="truck">
                <form:options items="${trucks}" itemLabel="reg_number" itemValue="truck_id"/>
            </form:select>
            <label for="truck"><s:message code="manager.neworder.truck.label"/></label>
            <form:errors path="truck"/>
            <div class="section"></div>
        </div>

        <button class="btn blue-grey waves-effect waves-light" type="submit" name="action"><s:message code="manager.neworder.button.select"/>
            <i class="material-icons left">person_add</i>
        </button>
        <a href="<c:url value="/manager_/orders/cancel"/>" class="btn blue-grey waves-effect waves-light"><s:message code="manager.neworder.button.cancel"/>
            <i class="material-icons left">undo</i>
        </a>
    </form:form>






</div>





<jsp:include page="../footer.jsp"/>