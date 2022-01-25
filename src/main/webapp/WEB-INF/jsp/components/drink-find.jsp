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

<div class="text-sm text-gray-400 mb-8">Quản lý đồ uống</div>

<div><a href="<c:url value="/admin/drinks/add" />"><i class="fas fa-plus"></i>Tạo mới</a></div>

<table id="drinks" style="width: 100%">
    <thead>
    <tr>
        <th class="drink">
            Mã số
        </th>
        <th class="drink">
            Tên đồ uống
        </th>
        <th class="drink">
            Giá tiền (VNĐ)
        </th>
        <th class="drink">
            Đơn vị tính
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

<script src="<c:url value="/js/drinks.js"/>"></script>