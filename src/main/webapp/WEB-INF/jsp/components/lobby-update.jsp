<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 04/12/2021
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="fs-1 text text-center">Thông tin sảnh tiệc</div>

<div class="container">

    <div class="form-control position-relative">
        <label for="lobby-code">Mã sảnh</label>
        <input type="text" id="lobby-code" class="form-control" disabled>

        <label for="lobby-name">Tên Sảnh <span class="text-danger">*</span> </label>
        <input type="text" id="lobby-name" class="form-control" aria-describedby="passwordHelpBlock">

        <label for="lobby-tables">Số bàn <span class="text-danger">*</span> </label>
        <input type="text" id="lobby-tables" class="form-control">

        <label for="lobby-status">Trạng thái <span class="text-danger">*</span> </label>
        <select class="form-select" id="lobby-status">
            <option selected disabled>Chọn trạng thái sảnh</option>
            <option value="1">Đang hoạt động</option>
            <option value="0">Không hoạt động</option>
        </select>

        <label for="lobby-name">Gía thuê sảnh (/4 giờ) <span class="text-danger">*</span> </label>
        <input type="text" id="lobby-price" class="form-control" aria-describedby="passwordHelpBlock">

        <button type="button" class="btn btn-success" id="update-btn">Cập nhật</button>
    </div>
</div>

<script src="<c:url value="/js/admin/update-lobby.js"/>"></script>