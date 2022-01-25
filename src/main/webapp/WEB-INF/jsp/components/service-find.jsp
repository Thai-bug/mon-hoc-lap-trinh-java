<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 08/12/2021
  Time: 01:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div><a href="<c:url value="/admin/services/add" />"><i class="fas fa-plus"></i>Tạo mới</a></div>

<table id="service" style="width: 100%">
    <thead>
    <tr>
        <th class="drink">
            Mã số
        </th>
        <th class="drink">
            Tên dịch vụ
        </th>
        <th class="drink">
            Giá tiền
        </th>

        <th class="drink">
            Trạng thái
        </th>
        <th class="drink">
            Ngày tạo
        </th>
        <th></th>
    </tr>
    </thead>
</table>

<script src="<c:url value="/js/services.js"/>"></script>