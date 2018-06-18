<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 17.06.2018
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="title" value="Cargoes List"/>
</jsp:include>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
</jsp:include>



<h5 class="indigo-text table-in"><s:message code="manager.neworder.cargolist.label"/></h5>
<form:form  method="post">
<table class="centered highlight bordered card-panel table">
    <thead>
    <tr>
        <th><s:message code="manager.neworder.cargolist.table.cargo"/></th>
        <th><s:message code="manager.neworder.cargolist.table.weight"/></th>
        <th><s:message code="manager.neworder.cargolist.table.loading"/></th>
        <th><s:message code="manager.neworder.cargolist.table.unloading"/></th>
        <th width="100"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${externals}" var="external" >
        <tr>
            <td>${external.name}</td>
            <td>${external.weight}</td>
            <td>${external.cityFrom.name}</td>
            <td>${external.cityTo.name}</td>
            <td>
                <label>
                    <input type="checkbox" name="ids" value="${external.external_id}"/>
                    <span></span>
                </label>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <button class="btn blue-grey waves-effect waves-light" type="submit" name="action"><s:message code="manager.neworder.cargolist.button.send"/>
        <i class="material-icons left">add_box</i>
    </button>
    <a class="btn blue-grey waves-effect waves-light" href="<c:url value="/manager_/orders/neworder"/>"><s:message code="manager.neworder.cargolist.button.back"/>
        <i class="material-icons left">undo</i>
    </a>
</form:form>
<jsp:include page="../footer.jsp"/>


