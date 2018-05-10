<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.05.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Drivers"/>
</jsp:include>

<jsp:include page="leftMenu.jsp">
        <jsp:param name="drivers" value="active"/>
</jsp:include>


    <table class="centered striped">
        <thead>
        <tr>
            <th>Personal code</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>City</th>
            <th>Worked time</th>
            <th>Status</th>
            <th width="100"></th>
            <th width="100"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <td>${driver.personal_code}</td>
                <td>${driver.first_name}</td>
                <td>${driver.last_name}</td>
                <td>${driver.user.email}</td>
                <td>${driver.city.name}</td>
                <td>${driver.worked_time}</td>
                <td>${driver.status.name()}</td>
                <td><a href="<c:url value='/manager_/edit_driver_${driver.driver_id}'/>">
                    <i class="material-icons right">edit</i></a></td>
                <td><a href="<c:url value='/manager_/delete_driver_${driver.driver_id}'/>">
                    <i class="material-icons right">delete_forever</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<div ><a class="waves-effect waves-light btn" href="<c:url value='/manager_/drivers/newdriver' />">Add new driver
    <i class="material-icons left">person_add</i></a></div>

<jsp:include page="footer.jsp"/>