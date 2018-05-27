<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 09.05.2018
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<div class="in-left-menu">
<div class="row light-blue-text text-accent-999">
    <div class="col s3">
        <ul id="nav-mobile" class="side-nav fixed z-depth-2 left-menu">
            <div style= "height: 350px" class="blue-grey darken-2">
                <p class="center white-text logo" >L O G I W E B</p>
                <p class="center white-text">${pageContext.request.userPrincipal.name} </p>
                <br>
                <br>
            </div>
            <div>
                <ul >
                    <li class="<c:out value="${param.orders}"/>"><a href="<c:url value="/manager_/orders"/>" style="color:#263238;">ORDERS</a></li>
                    <li class="<c:out value="${param.drivers}"/>"><a href="<c:url value="/manager_/drivers"/>" style="color:#263238;">DRIVERS</a></li>
                    <li class="<c:out value="${param.trucks}"/>"><a href="<c:url value="/manager_/trucks"/>" style="color:#263238;">TRUCKS</a></li>
                    <li><a href="<c:url value="/logout"/>" style="color: #263238;">LOG OUT</a></li>
                </ul>
            </div>
        </ul>
    </div>