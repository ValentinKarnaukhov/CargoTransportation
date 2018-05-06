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
<html>
<head>
    <title>New driver page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
</head>
<body>
<form:form modelAttribute="driver" class="form-horizontal" method="post">

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="personal_code">Personal code</label>
            <div class="col-md-7">
                <form:input type="text" path="personal_code" id="personal_code" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="firstName">First Name</label>
            <div class="col-md-7">
                <form:input type="text" path="first_name" id="firstName" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="lastName">Last name</label>
            <div class="col-md-7">
                <form:input type="text" path="last_name" id="lastName" class="form-control input-sm"/>
            </div>
        </div>
    </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="city">Cities</label>
                <div class="col-md-7">
                    <form:select id ="city" path="city.city_id" items="${cities}" class="form-control input-sm"
                                 itemLabel="name" itemValue="city_id"/>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="username">Username</label>
                <div class="col-md-7">
                    <form:input type="text" path="user.username" id="username" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="email">Email</label>
                <div class="col-md-7">
                    <form:input type="text" path="user.email" id="email" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="text" path="user.password" id="password" class="form-control input-sm"/>
                </div>
            </div>
        </div>

    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Add driver" class="btn btn-primary btn-sm">
        </div>
    </div>


</form:form>
</body>
</html>
