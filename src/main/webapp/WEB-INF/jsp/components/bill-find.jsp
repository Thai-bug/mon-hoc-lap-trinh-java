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

<div class="text-sm text-gray-400 mb-8">Quản lý đơn hàng</div>

<div><a href="<c:url value="/admin/bills/create" />"><i class="fas fa-plus"></i>Tạo mới</a></div>


<table id="bills" style="width: 100%">
    <thead>
    <tr>
        <th class="dịch vụ">
            Mã số
        </th>
        <th class="dịch vụ">
            Tên bữa tiệc
        </th>
        <th class="dịch vụ">
            Tổng tiền
        </th>

        <th class="dịch vụ">
            Tạm tính
        </th>

        <th class="dịch vụ">
            Nhân viên
        </th>

        <th class="dịch vụ">
            Trạng thái
        </th>

        <th class="dịch vụ">
            Ngày tạo
        </th>
        <th class="dịch vụ">
        </th>
    </tr>
    </thead>
</table>
<script src="<c:url value="/js/bills.js"/>"></script>