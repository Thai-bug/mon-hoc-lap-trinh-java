<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 04/11/2021
  Time: 01:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"
          integrity="sha512-wnea99uKIC3TJF7v4eKk4Y+lMz2Mklv18+r4na2Gn1abDRPPOeef95xTzdwGD9e6zXJBteMIhZ1+68QC5byJZw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
    />
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    <title>
        <tiles:insertAttribute name="title"/>
    </title>
</head>
<body>
<%--HEADER--%>
<div class="h-screen md:container md:mx-auto flex flex-auto box-border min-h-0 flex flex-row">
    <c:if test="${pageContext.request.userPrincipal.name !=null}">
    <div class="sider">
        <tiles:insertAttribute name="sider"/>
    </div>
    </c:if>

    <div class="layout w-full">
        <tiles:insertAttribute name="header"/>

        <%--CONTENT--%>
        <tiles:insertAttribute name="content"/>

        <%--FOOTER--%>
        <tiles:insertAttribute name="footer"/>
    </div>

</div>

<script src="<c:url value="/js/index.js"/>"></script>
</body>
</html>
