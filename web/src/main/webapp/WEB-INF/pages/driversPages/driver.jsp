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
            <div class="nav-wrapper blue darken-2">
                <a href="#" class="brand-logo"><img style="height: 70px;" src="<c:url value="/static/img/logo.png"/>"></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="<c:url value="/login?logout"/>">Log out</a></li>
                </ul>
            </div>
        </nav>
            <div style="float: left; margin-left: 10px; margin-top: 10px; margin-right: 2%">
                <input id="driver_id" value="${driver.driver_id}" type="hidden"/>
                    <table class="card-panel highlight responsive-table">
                        <thead>
                        <tr>
                            <th>Personal information</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>First name:</td>
                            <td>${driver.first_name}</td>
                        </tr>
                        <tr>
                            <td>Last name:</td>
                            <td>${driver.last_name}</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>${driver.user.email}</td>
                        </tr>
                        <tr>
                            <td>Personal code:</td>
                            <td>${driver.personal_code}</td>
                        </tr>
                        <tr>
                            <td>Hours worked:</td>
                            <td>${driver.worked_time}</td>
                        </tr>
                        <tr>
                            <td>Status:</td>
                            <td >
                                <select id="changeStatus" class="browser-default">
                                    <option value="1" <c:if test="${driver.status.name().equals('REST')}">selected</c:if>>REST</option>
                                    <option value="2" <c:if test="${driver.status.name().equals('WORK')}">selected</c:if>>WORK</option>
                                    <option value="3" <c:if test="${driver.status.name().equals('DRIVE')}">selected</c:if>>DRIVE</option>
                                </select>
                                <div class="acceptStatus">
                                    <button  id="ok" style="display: none"><i class="material-icons">save</i></button>
                                    <button id="decline" style="display: none"><i class="material-icons">cancel</i></button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>


                    <table class="card-panel highlight responsive-table">
                        <thead>
                        <tr>
                            <th>Additional information</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Order number:</td>
                            <td>${driver.truck.order.order_id}</td>
                        </tr>
                        <tr>
                            <td>Number of trucks:</td>
                            <td>${driver.truck.reg_number}</td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="card-panel highlight responsive-table">
                        <thead>
                        <tr>
                            <th>Co-drivers</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="co_driver" items="${driver.truck.drivers}">
                            <c:if test="${co_driver.driver_id!=driver.driver_id}">
                                <tr>
                                    <td>Code: ${co_driver.personal_code}</td>
                                    <td>${driver.last_name} ${driver.first_name}</td>
                                </tr>
                            </c:if>

                        </c:forEach>
                        </tbody>
                    </table>
            </div>
        <div style="float: left; width: 60%; margin-top: 10px">
            <table class="card-panel highlight responsive-table centered">
                <thead>
                <tr>
                    <th>Cargo number</th>
                    <th>Name</th>
                    <th>Weight</th>
                    <th>City</th>
                    <th>Operation</th>
                    <th>Status</th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${waypoints}" var="point">
                    <tr row_id="${point.order_waypoint_id}">
                        <td>${point.cargo.number}</td>
                        <td>${point.cargo.name}</td>
                        <td>${point.cargo.weight}</td>
                        <td>${point.city.name}</td>
                        <td>${point.operation}</td>
                        <td>
                            <div class="row_data">
                                <select class="cargoStatus browser-default" style="margin-top: -15px; margin-bottom: -15px">
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





<jsp:include page="../footer.jsp"/>
