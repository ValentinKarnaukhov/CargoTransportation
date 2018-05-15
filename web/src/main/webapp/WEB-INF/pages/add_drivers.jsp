<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 13.05.2018
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="New order page"/>
</jsp:include>

<jsp:include page="leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>

<h5 class="indigo-text">Create new order</h5>
<div class="section"></div>
<div  class="row" style="width: 90%; margin-right: 5%">

<form:form modelAttribute="order" method="post" action="/manager_/orders/neworder/finish" >
    <form:input path="truck" type="hidden"/>
    <%--<div class="row">--%>
        <%--<div class="input-field col s12">--%>
            <%--<form:select  id ="driver" path="drivers" items="${driverList}" itemLabel="first_name" itemValue="driver_id"/>--%>
            <%--<label for="driver">Driver</label>--%>
        <%--</div>--%>
    <%--</div>--%>
    <button class="btn waves-effect waves-light" type="submit" name="action">Select a drivers
        <i class="material-icons left">person_add</i>
    </button>
    <a href="<c:url value="/manager_/orders"/>" class="btn waves-effect waves-light">Cancel
        <i class="material-icons left">undo</i>
    </a>
</form:form>
</div>

<jsp:include page="footer.jsp"/>
