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
</head>
<body>
<div  class="row card-panel" style="width: 60%; margin-right: 40%">
    <form:form id="formValidate" modelAttribute="user" class="col s12" method="post" >

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
                <span class="helper-text" data-error="wrong" data-success="right">example@example.com</span>
                <form:errors path="email"/>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <button class="btn waves-effect waves-light" type="submit" name="action">Add manager
                    <i class="material-icons right">person_add</i>
                </button>
                <a href="<c:url value="/admin"/>" class="btn waves-effect waves-light">Cancel
                    <i class="material-icons right">undo</i>
                </a>
                <a href="<c:url value="/login?logout"/>" class="btn waves-effect waves-light">Cancel
                    <i class="material-icons right">cancel</i>
                </a>
            </div>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="<c:url value="/static/js/jquery-2.1.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.autocomplete.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/materialize.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.custom.js"/>"></script>
</body>
</html>
