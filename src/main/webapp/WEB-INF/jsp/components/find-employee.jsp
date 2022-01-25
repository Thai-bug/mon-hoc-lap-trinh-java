<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 17/11/2021
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="text-sm text-gray-400 mb-8">Quản lý nhân viên</div>

<div><a href="<c:url value="/admin/employees/create" />"><i class="fas fa-plus"></i>Tạo mới</a></div>

<table id="employees" style="width: 100%;">
    <thead>
    <tr>
        <th class="col">
            Mã số
        </th>
        <th class="col">
            Họ tên
        </th>
        <th class="col">
            Email
        </th>
        <th class="col">
            Quản lý
        </th>
        <th class="col">
            Trạng thái
        </th>
        <th class="col">
            Ngày tạo
        </th><th class="col">
            Ngày tạo
        </th>
    </tr>
    </thead>
</table>

<script src="<c:url value="/js/employees.js"/>"></script>