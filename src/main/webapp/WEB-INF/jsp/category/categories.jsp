<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 03/11/2021
  Time: 01:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>category</title>
    <link href="<c:url value="/css/tailwind.min.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Category</h1>
<ul>
<%--    <c:forEach var="cat" items="${categories}">--%>
<%--    <li>${cat.id} - ${cat.title}</li>--%>
<%--        </c:forEach>--%>
</ul>
</body>
</html>
