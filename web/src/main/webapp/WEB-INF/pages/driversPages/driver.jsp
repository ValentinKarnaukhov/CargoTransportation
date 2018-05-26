<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 03.05.2018
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Driver"/>
</jsp:include>
<body>
<div>
    <div>
        <nav>
            <div class="nav-wrapper blue-grey darken-2">
                <a href="#" class="brand-logo driver-logo">P E G A S U S</a>

                <ul id="nav-mobile" class="right hide-on-med-and-down ">
                    <li><a href="<c:url value="/logout"/>">LOG OUT</a></li>
                </ul>
            </div>
        </nav>
        <div class="pageContainer">
            <input id="driver_id" value="${driver.driver_id}" type="hidden"/>
            <div class="tableInContainer">
                <table class="card-panel bordered highlight responsive-table padding-table">
                    <thead>
                    <tr>
                        <th colspan="2">PERSONAL INFORMATION</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>FIRST NAME:</td>
                        <td>${driver.first_name}</td>
                    </tr>
                    <tr>
                        <td>LAST NAME:</td>
                        <td>${driver.last_name}</td>
                    </tr>
                    <tr>
                        <td>EMAIL:</td>
                        <td>${driver.user.email}</td>
                    </tr>
                    <tr>
                        <td>PERSONAL CODE:</td>
                        <td>${driver.personal_code}</td>
                    </tr>
                    <tr>
                        <td>HOURS WORKED:</td>
                        <td>${driver.worked_time}</td>
                    </tr>
                    <tr>
                        <td>CITY:</td>
                        <td>
                            <select id="changeCity" class="browser-default select-wrapper">
                                <c:forEach items="${cities}" var="city">
                                    <option value="${city.city_id}" <c:if test="${driver.city.city_id==city.city_id}">selected</c:if>>${city.name}</option>
                                </c:forEach>
                            </select>
                            <div class="acceptCity">
                                <button  id="city_ok" class="hide-button"><i class="material-icons">save</i></button>
                                <button id="city_decline" class="hide-button"><i class="material-icons">cancel</i></button>
                            </div>
                        </td>
                    </tr>
                    <tr  >
                        <td>STATUS:</td>
                        <td>
                            <select  id="changeStatus" class="browser-default select-wrapper">
                                <option value="1" <c:if test="${driver.status.name().equals('REST')}">selected</c:if>>REST</option>
                                <option value="2" <c:if test="${driver.status.name().equals('WORK')}">selected</c:if>>WORK</option>
                                <option value="3" <c:if test="${driver.status.name().equals('DRIVE')}">selected</c:if>>DRIVE</option>
                            </select>
                            <div class="acceptStatus">
                                <button  id="ok" class="hide-button"><i class="material-icons">save</i></button>
                                <button id="decline" class="hide-button"><i class="material-icons">cancel</i></button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="tableInContainer">
                <table class="card-panel bordered highlight responsive-table padding-table">
                    <thead>
                    <tr>
                        <th colspan="2">ADDITIONAL INFORMATION</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>ORDER NUMBER:</td>
                        <td>${driver.truck.order.order_id}</td>
                    </tr>
                    <tr>
                        <td>NUMBER OF TRUCK:</td>
                        <td>${driver.truck.reg_number}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="tableInContainer">
                <table class="card-panel bordered highlight responsive-table padding-table">
                    <thead>
                    <tr>
                        <th>CO-DRIVERS</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="co_driver" items="${driver.truck.drivers}">
                        <c:if test="${co_driver.driver_id!=driver.driver_id}">
                            <tr>
                                <td>CODE: ${co_driver.personal_code}</td>
                                <td>${driver.last_name} ${driver.first_name}</td>
                            </tr>
                        </c:if>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="main-table">
                <table class="card-panel highlight bordered responsive-table centered table">
                    <thead>
                    <tr>
                        <th>CARGO NUMBER</th>
                        <th>NAME</th>
                        <th>WEIGHT</th>
                        <th>CITY</th>
                        <th>OPERATION</th>
                        <th>STATUS</th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${waypoints}" var="point">
                        <tr row_id="${point.order_waypoint_id}">
                            <td>${point.cargo.number}</td>
                            <td>${point.cargo.name}</td>
                            <td>${point.cargo.weight}</td>
                            <td class="city">${point.city.name}</td>
                            <td class="operation">${point.operation}</td>
                            <td>
                                <div class="row_data">
                                    <select class="cargoStatus browser-default select-wrapper" >
                                        <option value="1" <c:if test="${point.status.name().equals('PROGRESS')}">selected</c:if>>PROGRESS</option>
                                        <option value="2" <c:if test="${point.status.name().equals('DONE')}">selected</c:if>>DONE</option>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <button row_id="${point.order_waypoint_id}" class="btn_ok" style="display: none"><i class="material-icons">save</i></button>
                                <button row_id="${point.order_waypoint_id}" class="btn_decline" style="display: none"><i class="material-icons">cancel</i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>





<jsp:include page="../footer.jsp"/>
