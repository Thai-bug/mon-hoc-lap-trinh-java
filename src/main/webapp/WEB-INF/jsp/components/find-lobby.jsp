<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 30/11/2021
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="text-sm text-gray-400 mb-8">Quản lý sảnh cưới</div>

<div><a href="<c:url value="/admin/lobby/create" />"><i class="fas fa-plus"></i>Tạo mới</a></div>

<table id="lobbies" style="width: 100%;">
    <thead>
    <tr>
        <th class="">
            Mã số
        </th>
        <th class="">
            Tên sảnh
        </th>
        <th class="">
            Số lượng khách
        </th>
        <th class="">
            Tiền thuê (VNĐ)
        </th>

        <th class="">
            Trạng thái
        </th>
        <th class="">
            Ngày tạo
        </th>
        <th class="">
        </th>
    </tr>
    </thead>
</table>

<script src="<c:url value="/js/lobbies.js"/>"></script>