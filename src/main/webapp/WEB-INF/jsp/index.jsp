<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 03/11/2021
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<div>

    <div >
        <div onclick="exportType()">Xuất báo cáo</div>
        <div>

            <canvas id="myChart" width="10" height="400"></canvas>
        </div>

    </div>


</div>

<script src="<c:url value="/js/general.js"/>"></script>