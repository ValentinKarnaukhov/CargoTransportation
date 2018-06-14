<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 13.05.2018
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Add drivers page"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>

<h5 class="indigo-text"><s:message code="manager.neworder.mainlabel"/></h5>
<div class="section"></div>
<div  class="row add-driver" >

<form:form class="card-panel" name="finish" modelAttribute="order" method="post" action="/manager_/orders/neworder/finish" >
    <form:input path="truck.truck_id" type="hidden"/>
    <form:input path="truck.max_drivers" type="hidden" class="maxDrivers"/>
    <div class="input-field col s12">
        <div class="grey-text lighten-1"><s:message code="manager.neworder.label.drivers"/></div>
        <form:select id ="driver" path="truck.drivers" multiple="true">
            <form:option value="" disabled="true"><s:message code="manager.neworder.item.first"/> ${amount} <s:message code="manager.neworder.item.second"/></form:option>
            <c:forEach items="${driverList}" var="driver">
                <form:option value="${driver.driver_id}">${driver.last_name} ${driver.first_name}</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="truck.drivers"/>
        <div class="errors" style="color: red"></div>
        <div class="section"></div>
    </div>
    <button class="btn waves-effect waves-light blue-grey" type="submit" name="action"><s:message code="manager.neworder.form.button.finish"/>
        <i class="material-icons left blue-grey">check</i>
    </button>
    <a href="<c:url value="/manager_/orders/neworder"/>" class="btn blue-grey waves-effect waves-light blue-grey"><s:message code="manager.neworder.form.button.back"/>
        <i class="material-icons left blue-grey">undo</i>
    </a>
</form:form>
</div>

<jsp:include page="../footer.jsp"/>
