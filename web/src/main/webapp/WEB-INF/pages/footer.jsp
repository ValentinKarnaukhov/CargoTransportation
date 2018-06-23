<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 09.05.2018
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</div>
</div>
<c:choose>
    <c:when test="${pageContext.response.locale=='ru'}">
        <script type="text/javascript" src="<c:url value="/static/js/min/rus.min.js"/>"></script>
    </c:when>
    <c:otherwise>
        <script type="text/javascript" src="<c:url value="/static/js/min/global.min.js"/>"></script>
    </c:otherwise>
</c:choose>
<script type="text/javascript" src="<c:url value="/static/js/min/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/min/materialize.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/min/custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/table.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/min/validate.min.js"/>"></script>
</body>
</html>
