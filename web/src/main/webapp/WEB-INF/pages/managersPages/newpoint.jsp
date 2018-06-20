<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 12.05.2018
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="New cargo"/>
</jsp:include>

<s:message code="manager.newcargo.label" var="label"/>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
    <jsp:param name="label" value="${label}"/>
</jsp:include>

<div  class="row card-panel driver-truck">
    <form:form  modelAttribute="waypoint" class="col s12" method="post" >
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargo.name" id="name" type="text" class="validate" required="true"/>
                <label for="name"><s:message code="manager.newcargo.label.name"/></label>
                <form:errors path="cargo.name"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargo.weight" id="weight" type="number" class="validate" required="true" placeholder="kg"/>
                <label for="weight"><s:message code="manager.newcargo.label.weight"/></label>
                <form:errors path="cargo.weight"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select id ="load_city" path="loadingCity" items="${cities}" itemLabel="name" itemValue="city_id"/>
                <label for="load_city"><s:message code="manager.newcargo.label.loading"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select id ="unload_city" path="unloadingCity" items="${cities}" itemLabel="name" itemValue="city_id"/>
                <label for="unload_city"><s:message code="manager.newcargo.label.unloading"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <button class="btn blue-grey waves-effect waves-light" type="submit" name="action"><s:message code="manager.newcargo.button.add"/>
                    <i class="material-icons right">add_box</i>
                </button>
                <a href="<c:url value="/manager_/orders/neworder"/>" class="btn blue-grey waves-effect waves-light"><s:message code="manager.newcargo.button.cancel"/>
                    <i class="material-icons right">undo</i>
                </a>
            </div>
        </div>
    </form:form>
</div>





<jsp:include page="../footer.jsp"/>
