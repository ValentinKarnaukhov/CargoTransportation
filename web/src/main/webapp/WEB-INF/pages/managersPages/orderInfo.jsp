<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 20.05.2018
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Order information"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>
<h5 class="indigo-text">Cargoes</h5>
<table  class="centered highlight card-panel">
    <thead>
    <tr>
        <th>Number</th>
        <th>Name</th>
        <th>Weight</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cargoes}" var="cargo">
        <tr>
            <td>${cargo.number}</td>
            <td>${cargo.name}</td>
            <td>${cargo.weight}</td>
            <td>${cargo.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value="/manager_/orders"/>" class="btn waves-effect waves-light">Back
    <i class="material-icons left">undo</i>
</a>


<jsp:include page="../footer.jsp"/>
