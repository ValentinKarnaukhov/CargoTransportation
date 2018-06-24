<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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

<s:message code="manager.orders.info.label" var="label"/>

<jsp:include page="../leftMenu.jsp">
    <jsp:param name="orders" value="active"/>
    <jsp:param name="label" value="${label}"/>
</jsp:include>

<div class="row" >
    <div class="col s12" >
        <ul class="tabs" style="background-color: #f3f3f3">
            <li class="tab col s6"><a class="active blue-grey-text" href="#table"><s:message code="manager.orders.info.table"/></a></li>
            <li class="tab col s6"><a class="blue-grey-text" href="#mapContainer"><s:message code="manager.orders.info.map"/></a></li>
        </ul>
    </div>
</div>

<div id="table" class="col s12">
    <table  class="centered highlight bordered card-panel table">
        <thead>
        <tr>
            <th><s:message code="manager.orders.info.table.number"/></th>
            <th><s:message code="manager.orders.info.table.name"/></th>
            <th><s:message code="manager.orders.info.table.weight"/></th>
            <th><s:message code="manager.orders.info.table.status"/></th>
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
    <a href="<c:url value="/manager_/orders"/>" class="btn blue-grey waves-effect waves-light"><s:message code="manager.orders.info.button.back"/>
        <i class="material-icons left">undo</i>
    </a>
</div>

<div id="mapContainer" class="col s12">



    <script src="https://api-maps.yandex.ru/2.1/?lang=en_US" type="text/javascript">
    </script>
    <script type="text/javascript">
        function init () {
            var multiRoute = new ymaps.multiRouter.MultiRoute({
                referencePoints: [
                    <c:forEach items="${cargoes}" var="cargo">
                        [${cargo.orderWaypoint.get(0).city.latitude},${cargo.orderWaypoint.get(0).city.longitude}],
                        [${cargo.orderWaypoint.get(1).city.latitude},${cargo.orderWaypoint.get(1).city.longitude}],
                    </c:forEach>
                ],
                params: {
                    results: 1
                }
            }, {
                boundsAutoApply: true
            });

            var myMap = new ymaps.Map('map', {
                center: [55.750625, 37.626],
                zoom: 5
            });

            // Добавляем мультимаршрут на карту.
            myMap.geoObjects.add(multiRoute);
        }

        ymaps.ready(init);
    </script>
    <div id="map" style="height: 75%" class="card-panel"></div>
</div>


<jsp:include page="../footer.jsp"/>
