<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<h5 class="indigo-text" style="margin-left: 1%">Create new truck</h5>
<div class="section"></div>
<div  class="row card-panel" style="width: 60%; margin-right: 40%">
    <form:form id="formValidate" modelAttribute="truck" class="col s12" method="post" >
        <div class="row">
            <div class="input-field col s12">
                <form:input path="reg_number" id="reg_number" type="text" class="validate" required="true"/>
                <label for="reg_number">Registration number</label>
                <span class="helper-text" data-error="wrong" data-success="right">Example: ab12345</span>
                <form:errors path="reg_number"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="max_drivers" id="max_drivers" type="number" class="validate" required="true"/>
                <label for="max_drivers">Max drivers</label>
                <form:errors path="max_drivers"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="capacity" id="capacity" type="number" class="validate" required="true"/>
                <label for="capacity">Capacity</label>
                <form:errors path="capacity"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select id ="status" path="status" items="${statuses}"/>
                <label for="status">Status</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <form:select id ="city" path="city" items="${cities}" itemLabel="name" itemValue="city_id"/>
                <label for="city">City</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <button class="btn waves-effect waves-light" type="submit" name="action">Add truck
                    <i class="material-icons right">local_shipping</i>
                </button>
                <a href="<c:url value="/manager_/trucks"/>" class="btn waves-effect waves-light">Cancel
                    <i class="material-icons right">undo</i>
                </a>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="../footer.jsp"/>

