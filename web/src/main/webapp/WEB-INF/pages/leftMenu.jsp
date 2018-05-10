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
<div style="margin-left: 280px; margin-right: 100px; margin-top: 50px">
<div class="row light-blue-text text-accent-999">
    <div class="col s3">
        <ul id="nav-mobile" class="side-nav fixed z-depth-2" style="width: 240px;">
            <div style="background-color: #0D47A1; height: 350px; width: 400px;">
                <img src="<c:url value="/static/img/Fry_rev.jpg"/>" class="circle z-depth-2" style="width:130px; height:130px; margin-top:130px; margin-left: 55px;">
                <p style="margin-left: 90px; margin-top:10px; color:#FFFFFF">${pageContext.request.userPrincipal.name} </p>
                <br>
                <br>
            </div>
            <div>
                <ul >
                    <li class="<c:out value="${param.orders}"/>"><a href="<c:url value="/manager_/orders"/>" style="color:#9F9F9F;">Orders</a></li>
                    <li class="<c:out value="${param.drivers}"/>"><a href="<c:url value="/manager_/drivers"/>" style="color:#9F9F9F;">Drivers</a></li>
                    <li class="<c:out value="${param.trucks}"/>"><a href="<c:url value="/manager_/trucks"/>" style="color:#9F9F9F;">Trucks</a></li>
                    <li><a href="<c:url value="/logout"/>" style="color:#9F9F9F;">Sign out</a></li>
                </ul>
            </div>
        </ul>
    </div>