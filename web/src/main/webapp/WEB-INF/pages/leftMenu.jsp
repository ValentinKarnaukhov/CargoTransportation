<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 09.05.2018
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<body>

<nav>
    <div class="nav-wrapper  blue-grey darken-1">
        <a href="#" class="brand-logo" style="margin-left: 22%"><c:out value="${param.label}"/></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a class="white-text" href="?lang=en">ENG</a></li>
            <li><a class="white-text" href="?lang=ru">RU</a></li>
        </ul>
    </div>
</nav>

<div class="in-left-menu">
<div class="row light-blue-text text-accent-999">
    <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul id="slide-out" class="sidenav sidenav-fixed z-depth-2 left-menu">
            <div style= "height: 170px; background-image: url('/static/img/sidenav.svg')" class="blue-grey">
                <p class="center white-text logo">L O G I W E B</p>
                <p class="center white-text">${pageContext.request.userPrincipal.name}</p>
            </div>
            <div>
                <ul >
                    <li class="<c:out value="${param.orders}"/>"><a href="<c:url value="/manager_/orders"/>" style="color:#263238;"><i class="material-icons left">description</i><s:message code="manager.menu.orders"/></a></li>
                    <li class="<c:out value="${param.drivers}"/>"><a href="<c:url value="/manager_/drivers"/>" style="color:#263238;"><i class="material-icons left">person</i><s:message code="manager.menu.drivers"/></a></li>
                    <li class="<c:out value="${param.trucks}"/>"><a href="<c:url value="/manager_/trucks"/>" style="color:#263238;"><i class="material-icons left">local_shipping</i><s:message code="manager.menu.trucks"/></a></li>
                    <li><a href="<c:url value="/logout"/>" style="color: #263238;"><i class="material-icons left">exit_to_app</i><s:message code="manager.menu.logout"/></a></li>
                </ul>
            </div>
        </ul>


