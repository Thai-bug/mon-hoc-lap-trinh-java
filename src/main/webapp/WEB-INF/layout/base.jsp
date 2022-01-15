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
<html data-theme="cupcake">
<head>
    <meta charset="utf-8"/>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/css/main.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/css/utils.css"/>" rel="stylesheet" />

    <link href="<c:url value="/css/datepicker.min.css"/>" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/daisyui@1.16.2/dist/full.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2/dist/tailwind.min.css" rel="stylesheet" type="text/css"/>
    <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
    />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>

    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"
            integrity="sha512-qTXRIMyZIFb8iQcfjXWCO8+M5Tbc38Qi5WzdPOYZHIlZpzBHG3L3by84BBBOiRGiEb7KKtAOAs5qYdUiZiQNNQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    <script src="https://raw.githubusercontent.com/msroot/Notify.js/master/Notify.js" defer></script>
    <script src="<c:url value="/js/datepicker.min.js"/>" rel="stylesheet" ></script>

    <title>
        <tiles:insertAttribute name="title"/>
    </title>
</head>
<body>
<%--HEADER--%>
<div id="notifications"></div>
<div class="h-screen md:container mx-auto flex flex-auto box-border min-h-0 flex flex-row">
    <c:if test="${param.accessDenied !=null}">
        lỗi truy cập
    </c:if>
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
