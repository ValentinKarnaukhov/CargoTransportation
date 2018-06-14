<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
    <div style="float: right">
        <span>
            <a href="?lang=en">ENG</a>
            <a href="?lang=ru">RU</a>
        </span>
    </div>
<div class="row light-blue-text text-accent-999">
    <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul id="slide-out" class="sidenav sidenav-fixed z-depth-2 left-menu">
            <div style= "height: 350px" class="blue-grey">
                <p class="center white-text logo" >L O G I W E B</p>
                <p class="center white-text">${pageContext.request.userPrincipal.name} </p>
                <br>
                <br>
            </div>
            <div>
                <ul >
                    <li class="<c:out value="${param.orders}"/> center"><a href="<c:url value="/manager_/orders"/>" style="color:#263238;"><s:message code="manager.menu.orders"/></a></li>
                    <li class="<c:out value="${param.drivers}"/> center"><a href="<c:url value="/manager_/drivers"/>" style="color:#263238;"><s:message code="manager.menu.drivers"/></a></li>
                    <li class="<c:out value="${param.trucks}"/> center"><a href="<c:url value="/manager_/trucks"/>" style="color:#263238;"><s:message code="manager.menu.trucks"/></a></li>
                    <li class="center"><a href="<c:url value="/logout"/>" style="color: #263238;"><s:message code="manager.menu.logout"/></a></li>
                </ul>
            </div>
        </ul>


