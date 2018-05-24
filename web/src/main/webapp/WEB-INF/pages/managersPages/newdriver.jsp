<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="New driver page"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="drivers" value="active"/>
</jsp:include>


<h5 class="indigo-text">CREATE NEW DRIVER</h5>
<div class="section"></div>
<div  class="row card-panel driver-truck">
    <form:form id="formValidate" modelAttribute="driver" class="col s12" method="post" >
        <div class="row">
            <div class="input-field col s6">
                <form:input path="first_name" id="first_name" type="text" class="validate" required="true"/>
                <label for="first_name">First Name</label>
                <form:errors path="first_name"/>
            </div>
            <div class="input-field col s6">
                <form:input path="last_name" id="last_name" type="text" class="validate" required="true"/>
                <label for="last_name">Last Name</label>
                <form:errors path="last_name"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="user.email" id="email" type="email" class="validate" required="true"/>
                <label for="email">Email</label>
                <span class="helper-text" data-error="wrong" data-success="right">example@example.com</span>
                <form:errors path="user.email"/>
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
                <button class="btn blue-grey waves-effect waves-light" type="submit" name="action">Add driver
                    <i class="material-icons right">person_add</i>
                </button>
                <a href="<c:url value="/manager_/drivers"/>" class="btn waves-effect waves-light blue-grey">Cancel
                    <i class="material-icons right">undo</i>
                </a>
            </div>
        </div>
    </form:form>
</div>





<jsp:include page="../footer.jsp"/>
