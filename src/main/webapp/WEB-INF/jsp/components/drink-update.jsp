<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 08/12/2021
  Time: 03:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="fs-1 text text-center">Thông tin đồ uống</div>

<div class="container">

    <div class="form-control position-relative">
        <label for="drink-code">Mã sảnh</label>
        <input type="text" id="drink-code" class="form-control" disabled>

        <label for="drink-name">Tên Sảnh <span class="text-danger">*</span> </label>
        <input type="text" id="drink-name" class="form-control" aria-describedby="passwordHelpBlock" >

        <label for="drink-status">Trạng thái <span class="text-danger">*</span> </label>
        <select class="form-select" id="drink-status" >
            <option selected >Chọn trạng thái sảnh</option>
            <option value="1">Đang hoạt động</option>
            <option value="0">Không hoạt động</option>
        </select>

        <label for="retail-price">Đơn giá <span class="text-danger">*</span> </label>
        <input type="text" id="retail-price" class="form-control" >

        <label for="unit">Đơn vị tính <span class="text-danger">*</span> </label>
        <input type="text" id="unit" class="form-control" >

        <div class="mt-5">
            <textarea id="description" ></textarea>
        </div>

        <div class="text-center mt-2">
            <button type="button" class="btn btn-success" id="update-btn">Cập nhật</button>
        </div>
    </div>

    <%--    <div>Danh sách các đơn hàng</div>--%>

    <div class="mb-2 mt-2"></div>
    <div id="pagintaion-bills"></div>
</div>

<script src="<c:url value="/js/admin/update-drink.js"/>"></script>
