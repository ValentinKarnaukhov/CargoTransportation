<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Drivers"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
        <jsp:param name="drivers" value="active"/>
</jsp:include>

    <h5 class="indigo-text">Driver list</h5>
    <table class="centered highlight card-panel">
        <thead>
        <tr>
            <th>Personal code</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>City</th>
            <th>Status</th>
            <th>Order</th>
            <th>Worked time</th>
            <th width="50"></th>
            <th width="50"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <td >${driver.personal_code}</td>
                <td >${driver.first_name}</td>
                <td >${driver.last_name}</td>
                <td >${driver.user.email}</td>
                <td >${driver.city.name}</td>
                <td>${driver.status}</td>
                <td>${driver.truck.order.order_id}</td>
                <td>${driver.worked_time}</td>

                    <%--<select class="browser-default" style="background-color: transparent; margin-top: -10px; margin-bottom: -10px">--%>
                        <%--<c:forEach items="${cities}" var="city">--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${city.city_id==driver.city.city_id}">--%>
                                    <%--<option selected value="${city.city_id}">${city.name}</option>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${city.city_id!=driver.city.city_id}">--%>
                                    <%--<option value="${city.city_id}">${city.name}</option>--%>
                                <%--</c:when>--%>
                            <%--</c:choose>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>


                <%--<td class="status">--%>
                    <%--<select class="browser-default" style="background-color: transparent; margin-top: -10px; margin-bottom: -10px">--%>
                        <%--<c:forEach items="${statuses}" var="status">--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${driver.status==status}">--%>
                                    <%--<option selected>${status}</option>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${driver.status!=status}">--%>
                                    <%--<option >${status}</option>--%>
                                <%--</c:when>--%>
                            <%--</c:choose>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</td>--%>
                <td><a href="<c:url value='/manager_/edit_driver_${driver.driver_id}'/>">
                    <i class="material-icons">edit</i></a></td>
                <td><a href="<c:url value='/manager_/delete_driver_${driver.driver_id}'/>">
                    <i class="material-icons">delete_forever</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<div ><a class="waves-effect waves-light btn" href="<c:url value='/manager_/drivers/newdriver' />">Add new driver
    <i class="material-icons left">person_add</i></a></div>

<jsp:include page="../footer.jsp"/>