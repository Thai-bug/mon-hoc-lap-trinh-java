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
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="/css/tailwind.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/@tailwindcss/custom-forms@0.2.1/dist/custom-forms.css"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
    />
    <title>
        <tiles:insertAttribute name="title" />
    </title>
</head>
<body>
<%--HEADER--%>
<div class="md:container md:mx-auto my-4">
    <tiles:insertAttribute name="header" />

    <%--CONTENT--%>
    <tiles:insertAttribute name="content" />

    <%--FOOTER--%>
    <tiles:insertAttribute name="footer" />
</div>

<script src="<c:url value="/js/index.js"/>"></script>
</body>
</html>
