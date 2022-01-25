<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 25/01/2022
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<div class="container">
    <div style="font-weight: bold; text-transform: uppercase; font-size: 26px; margin-top: 10px;">Danh sách thức ăn</div>

    <div class="d-flex justify-content-end">
        <input type="text" placeholder="Tìm thức ăn theo tên" id="keyword" />
    </div>

    <div class="container">

    </div>
    <div id="pagintaion-foods" style="margin-top: 10px"></div>
</div>

<script src="<c:url value="/js/client/foods.js"/>"></script>
