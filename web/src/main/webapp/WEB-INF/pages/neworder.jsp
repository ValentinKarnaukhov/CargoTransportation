<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 07.05.2018
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="New order page"/>
</jsp:include>

<jsp:include page="leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>
<h5 class="indigo-text" style="margin-left: 1%">Create new driver</h5>
<div class="section"></div>
<div  class="row" style="width: 90%; margin-right: 10%">

    <ul id="tabs-swipe-demo" class="tabs">
        <li class="tab col s6"><a class="active"href="#test-swipe-1">Test 1</a></li>
        <li class="tab col s6"><a href="#test-swipe-2">Test 2</a></li>
        <li class="tab col s6"><a href="#test-swipe-3" id="selectDriver">Test 3</a></li>
    </ul>
    <!-- FIRST STEP -->
    <div id="test-swipe-1" class="col s12 ">

        <table class="centered striped">
            <thead>
            <tr>
                <th>Cargo</th>
                <th>Weight</th>
                <th>City of loading</th>
                <th>City of unloading</th>
                <th width="50"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${waypoints}" var="waypoint">
                <tr>
                    <td>${waypoint.cargo.name}</td>
                    <td>${waypoint.cargo.weight}</td>
                    <td>${waypoint.loadingCity.name}</td>
                    <td>${waypoint.unloadingCity.name}</td>
                    <td><a href="<c:url value='/manager_/orders/delete_point_${waypoints.indexOf(waypoint)}'/>">
                        <i class="material-icons">delete_forever</i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div ><a class="waves-effect waves-light btn" href="<c:url value='/manager_/orders/newcargo' />">Add new cargo
            <i class="material-icons left">add_box</i></a></div>

    </div>

    <!-- SECOND STEP -->

    <div id="test-swipe-2" class="col s12 ">

        <form:form  modelAttribute="order" class="col s12" method="post" >
            <div class="row">
                <div class="input-field col s12">
                    <form:select id ="truck" path="truck" items="${trucks}" itemLabel="reg_number" itemValue="truck_id"/>
                    <label for="truck">Truck</label>
                </div>
            </div>
        </form:form>






    </div>
    <div id="test-swipe-3" class="col s12"></div>

</div>

<jsp:include page="footer.jsp"/>