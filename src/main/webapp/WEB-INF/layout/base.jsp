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
    <link href="<c:url value="/css/utils.css"/>" rel="stylesheet"/>

    <link href="<c:url value="/css/datepicker.min.css"/>" rel="stylesheet"/>
    <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
    />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css"
          rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/select/1.3.3/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.26.0/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"
            integrity="sha512-qTXRIMyZIFb8iQcfjXWCO8+M5Tbc38Qi5WzdPOYZHIlZpzBHG3L3by84BBBOiRGiEb7KKtAOAs5qYdUiZiQNNQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <script src="https://raw.githubusercontent.com/msroot/Notify.js/master/Notify.js" defer></script>
    <script src="<c:url value="/js/datepicker.min.js"/>" rel="stylesheet"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.24.0/axios.min.js"
            integrity="sha512-u9akINsQsAkG9xjc1cnGF4zw5TFDwkxuc9vUp5dltDWYCSmyd0meygbvgXrlc/z7/o4a19Fb5V0OUE58J7dcyw=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"
            integrity="sha512-TW5s0IT/IppJtu76UbysrBH9Hy/5X41OTAbQuffZFU6lQ1rdcLHzpU5BzVvr/YFykoiMYZVWlr/PX1mDcfM9Qg=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.7.12/xlsx.core.min.js"></script>
    <title>
        <tiles:insertAttribute name="title"/>
    </title>
</head>
<body>
<%--HEADER--%>
<div id="notifications"></div>
<c:if test="${pageContext.request.userPrincipal.name == null}">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link " href="/restaurant_war_exploded/lobbies">Sảnh tiệc</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/restaurant_war_exploded/drinks">Đồ uống</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/restaurant_war_exploded/foods">Thức ăn</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/restaurant_war_exploded/services">Dịch vụ</a>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <a href="/restaurant_war_exploded/login" class="py-5 px-3">Đăng nhập</a>
                    </form>
                </div>
            </div>
        </nav>

        <tiles:insertAttribute name="client-content"/>
    </div>
</c:if>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <c:if test="${param.accessDenied !=null}">
            lỗi truy cập
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name !=null}">
            <tiles:insertAttribute name="sider"/>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div class="col py-3">
                <tiles:insertAttribute name="content"/>
            </div>
        </c:if>

    </div>
</div>

<script src="<c:url value="/js/index.js"/>"></script>
</body>
</html>
