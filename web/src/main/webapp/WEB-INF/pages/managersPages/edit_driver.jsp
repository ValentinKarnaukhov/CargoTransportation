<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 06.05.2018
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Edit driver"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="drivers" value="active"/>
</jsp:include>


<h5 class="indigo-text" style="margin-left: 1%">Edit driver</h5>
<div class="section"></div>
<div  class="row card-panel" style="width: 60%; margin-right: 40%">
    <form:form modelAttribute="driver" class="col s12" method="post" >
        <form:input path="personal_code" type="hidden"/>
        <form:input path="user.email" type="hidden"/>
        <form:input path="truck.truck_id" type="hidden"/>
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
        <%--<div class="row">--%>
            <%--<div class="input-field col s12">--%>
                <%--<form:input path="user.email" id="email" type="email" class="validate" required="true"/>--%>
                <%--<label for="email">Email</label>--%>
                <%--<span class="helper-text" data-error="wrong" data-success="right">example@example.com</span>--%>
                <%--<form:errors path="user.email"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="row">
            <div class="input-field col s12">
                <form:select id ="city" path="city">
                    <c:forEach items="${cities}" var="item">
                        <c:choose>
                            <c:when test="${item.city_id==driver.city.city_id}">
                                <form:option value="${item.city_id}" selected="true">${item.name}</form:option>
                            </c:when>
                            <c:when  test="${item.city_id!=driver.city.city_id}">
                                <form:option value="${item.city_id}">${item.name}</form:option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </form:select>

                <label for="city">City</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select id ="status" path="status">
                    <c:forEach items="${statuses}" var="item">
                        <c:choose>
                            <c:when test="${item.name()==driver.status.name()}">
                                <form:option value="${item}" selected="true">${item.name()}</form:option>
                            </c:when>
                            <c:when  test="${item.name()!=driver.status.name()}">
                                <form:option value="${item}">${item.name()}</form:option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </form:select>
                <label for="status">Status</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="worked_time" id="worked_time" type="number" class="validate" required="true"/>
                <label for="worked_time">Worked time</label>
                <form:errors path="worked_time"/>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <button class="btn waves-effect waves-light" type="submit" name="action">Edit driver
                    <i class="material-icons right">edit</i>
                </button>
                <a href="<c:url value="/manager_/drivers"/>" class="btn waves-effect waves-light">Cancel
                    <i class="material-icons right">undo</i>
                </a>
            </div>
        </div>
    </form:form>
</div>

















<%--<html>--%>
<%--<head>--%>
    <%--<title>Edit driver</title>--%>
    <%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form:form modelAttribute="driver" class="form-horizontal" method="post">--%>

    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="personal_code">Personal code</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:input type="text" path="personal_code" id="personal_code" class="form-control input-sm"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="firstName">First Name</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:input type="text" path="first_name" id="firstName" class="form-control input-sm"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="lastName">Last name</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:input type="text" path="last_name" id="lastName" class="form-control input-sm"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>


    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="city">Cities</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:select id ="city" path="city.city_id" items="${cities}" class="form-control input-sm"--%>
                             <%--itemLabel="name" itemValue="city_id"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="status">Cities</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:select id ="status" path="status" items="${statuses}" class="form-control input-sm"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>


    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="username">Username</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:input type="text" path="user.username" id="username" class="form-control input-sm"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable" for="email">Email</label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:input type="text" path="user.email" id="email" class="form-control input-sm"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>





    <%--<div class="row">--%>
        <%--<div class="form-actions floatRight">--%>
            <%--<input type="submit" value="Edit" class="btn btn-primary btn-sm">--%>
        <%--</div>--%>
    <%--</div>--%>


<%--</form:form>--%>
<jsp:include page="../footer.jsp"/>