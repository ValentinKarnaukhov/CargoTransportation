<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin page</title>
    <link href="<c:url value="/static/css/material-icons.css"/>" rel="stylesheet">
    <link href="<c:url value='/static/css/materialize.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/styles.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/table.css' />" rel="stylesheet"/>
</head>
<body>
<nav class="nav-extended">
    <div class="nav-wrapper blue-grey">
        <a href="#" class="brand-logo">L O G I W E B</a>
        <ul id="nav-mobile" class="right">
            <li><a href="<c:url value="/manager_"/>">MANAGER</a></li>
            <li><a href="<c:url value="/logout"/>">LOGOUT</a></li>

        </ul>
    </div>
    <div class="nav-content  blue-grey lighten-1">
        <ul class="tabs tabs-transparent">
            <li class="tab"><a class="active"  href="#1">USERS</a></li>
            <li class="tab"><a href="#2">TRUCKS</a></li>
            <li class="tab"><a href="#3">ADD MANAGER</a></li>
        </ul>
    </div>
</nav>
<%--FIRST--%>
<div id="1" class="col s12" style="margin-left: 2%; margin-right: 2%; margin-top: 1%">
    <table class="centered bordered highlight card-panel table">
        <thead>
        <tr>
            <th>ID</th>
            <th>USERNAME</th>
            <th>EMAIL</th>
            <th>ROLE</th>
            <th>ENABLED</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.user_id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--SECOND--%>
<div id="2" class="col s12" style="margin-left: 2%; margin-right: 2%; margin-top: 1%">

    <table class="centered bordered highlight card-panel table">
        <thead>
        <tr>
            <th>ID</th>
            <th>REGISTRATION NUMBER</th>
            <th>MAX DRIVERS</th>
            <th>CAPACITY</th>
            <th>STATUS</th>
            <th>CITY</th>
            <th>ORDER</th>
            <th>ENABLED</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trucks}" var="truck">
            <tr>
                <td>${truck.truck_id}</td>
                <td>${truck.reg_number}</td>
                <td>${truck.max_drivers}</td>
                <td>${truck.capacity}</td>
                <td>${truck.status}</td>
                <td>${truck.city.name}</td>
                <td>${truck.order.order_id}</td>
                <td>${truck.enabled}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<div id="3" class="col s12">
    <div  class="row card-panel driver-truck" style="margin-left: 20%; margin-top: 1%">
        <form:form id="user" modelAttribute="user" class="col s12" method="post" >

            <div class="row">
                <div class="input-field col s12">
                    <form:input path="username" id="username" type="text" class="validate" required="true"/>
                    <label for="username">Username</label>
                    <form:errors path="username"/>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <form:input path="email" id="email" type="email" class="validate" required="true"/>
                    <label for="email">Email</label>
                    <span class="helper-text">example@example.com</span>
                    <form:errors path="email"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <button class="btn blue-grey waves-effect waves-light" type="submit" name="action">Add manager
                        <i class="material-icons right">person_add</i>
                    </button>
                    <a href="<c:url value="/admin"/>" class="btn waves-effect blue-grey waves-light">Cancel
                        <i class="material-icons right">undo</i>
                    </a>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/static/js/global.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery-2.1.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/materialize.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/table.js"/>"></script>
</body>
</html>
