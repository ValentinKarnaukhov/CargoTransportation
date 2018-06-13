<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/materialize.css"/>"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/styles.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    <a href="?lang=ru">ru</a>
    </span>
<main>
    <div style="text-align: center;">
        <img class="responsive-img" style="height: 150px;" src="<c:url value="/static/img/logo.png"/>" />
        <div class="section"></div>
        <h5 class="blue-grey-text">PLEASE, LOGIN TO YOUR ACCOUNT</h5>
        <h5><spring:message code="name"/></h5>
        <div class="section"></div>
        <div class="container">
            <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 43px 0 43px; border: 1px solid #EEE;">
                <form class="col s12" method="post" action="<c:url value="/login"/>">
                    <div class='row'>
                        <div class='col s12'>
                        </div>
                    </div>

                    <div class='row'>
                        <div class='input-field col s12'>
                            <input class='validate' type='email' name='email' id='email' required="true"/>
                            <label for='email'>Enter your email</label>
                        </div>
                    </div>

                    <div class='row'>
                        <div class='input-field col s12'>
                            <input class='validate' type='password' name='password' id='password'required="true"/>
                            <label for='password'>Enter your password</label>
                        </div>
                    </div>

                    <br />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div style="text-align: center;">
                        <div class='row'>
                            <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect blue-grey'>Login</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</main>
<script src="https://api-maps.yandex.ru/1.1/index.xml" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery-2.1.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.autocomplete.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/materialize.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.custom.js"/>"></script>
</body>
</html>