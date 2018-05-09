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
<html>
<head>
    <title>New truck</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
</head>
<body>
<form:form modelAttribute="truck" class="form-horizontal" method="post">

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="reg_number">Registration number</label>
            <div class="col-md-7">
                <form:input type="text" path="reg_number" id="reg_number" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="max_drivers">Max drivers</label>
            <div class="col-md-7">
                <form:input type="text" path="max_drivers" id="max_drivers" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="capacity">Capacity</label>
            <div class="col-md-7">
                <form:input type="text" path="capacity" id="capacity" class="form-control input-sm"/>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="city">City</label>
            <div class="col-md-7">
                <form:select id ="city" path="city.city_id" items="${cities}" class="form-control input-sm"
                             itemLabel="name" itemValue="city_id"/>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="status">Status</label>
            <div class="col-md-7">
                <form:select id ="status" path="status" items="${statuses}" class="form-control input-sm"/>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Add truck" class="btn btn-primary btn-sm">
        </div>
    </div>


</form:form>
</body>
</html>
