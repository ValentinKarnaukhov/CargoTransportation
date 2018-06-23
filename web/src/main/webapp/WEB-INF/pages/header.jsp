<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 09.05.2018
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
    <title><c:out value="${param.title}"/></title>
    <link href="<c:url value="/static/css/min/material-icons.min.css"/>" rel="stylesheet">
    <link href="<c:url value='/static/css/min/materialize.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/min/styles.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/min/table.min.css' />" rel="stylesheet"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>

