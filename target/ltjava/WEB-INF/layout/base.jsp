<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 04/11/2021
  Time: 01:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="/css/tailwind.min.css"/>" rel="stylesheet"/>
    <title>
        <tiles:insertAttribute name="title" />
    </title>
</head>
<body>
<%--HEADER--%>
<tiles:insertAttribute name="header" />

<%--CONTENT--%>
<tiles:insertAttribute name="content" />

<%--FOOTER--%>
<tiles:insertAttribute name="footer" />

</body>
</html>
