<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 06.05.2018
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="New truck page"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="trucks" value="active"/>
</jsp:include>

<h5 class="indigo-text"><s:message code="manager.newtruck.label"/></h5>
<div class="section"></div>
<div  class="row card-panel driver-truck">
    <form:form id="truck" modelAttribute="truck" class="col s12" method="post" >
        <div class="row">
            <div class="input-field col s12">
                <form:input path="reg_number" id="reg_number" type="text" class="validate" required="true"/>
                <label for="reg_number"><s:message code="manager.newtruck.form.regnumber"/></label>
                <span class="helper-text" ><s:message code="manager.trucks.example"/></span>
                <form:errors path="reg_number"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="max_drivers" id="max_drivers" type="number" class="validate" required="true"/>
                <label for="max_drivers"><s:message code="manager.newtruck.form.maxdrivers"/></label>
                <form:errors path="max_drivers"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="capacity" id="capacity" type="number" class="validate" required="true" placeholder="kg"/>
                <label for="capacity"><s:message code="manager.newtruck.form.capacity"/></label>
                <form:errors path="capacity"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select id ="status" path="status" items="${statuses}"/>
                <label for="status"><s:message code="manager.newtruck.form.status"/></label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <form:select id ="city" path="city" items="${cities}" itemLabel="name" itemValue="city_id"/>
                <label for="city"><s:message code="manager.newtruck.form.city"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <button class="btn blue-grey waves-effect waves-light" type="submit" name="action"><s:message code="manager.newtruck.button.add"/>
                    <i class="material-icons right">local_shipping</i>
                </button>
                <a href="<c:url value="/manager_/trucks"/>" class="btn blue-grey waves-effect waves-light"><s:message code="manager.newtruck.button.cancel"/>
                    <i class="material-icons right">undo</i>
                </a>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="../footer.jsp"/>

