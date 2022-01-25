<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 26/01/2022
  Time: 01:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-sm text-gray-400 mb-8">Quản lý bình luận</div>

<div class="container">
    <div class="text-center fs-2 fw-bold ">Kiểm duyệt bình luận</div>
    <div>
        <label for="code">Mã bình luận</label>
        <input type="text" id="code" class="form-control" disabled>
    </div>

    <div>
        <label for="type">Loại bình luận</label>
        <input type="text" id="type" class="form-control" disabled>
    </div>

    <div>
        <label for="rating">Điểm</label>
        <input id="rating" name="rating" type="number"  class="form-control" disabled>
    </div>

    <div>
        <label for="createdAt">Ngày tạo</label>
        <input id="createdAt" class="form-control" disabled>
    </div>

    <div class="mt-3">
        <textarea id="comment" class="form-control" disabled></textarea>
    </div>

    <div class="mt-3 text-center">
        <button type="button" class="btn btn-secondary" id="disabled">
            Ẩn bình luận
        </button>

        <button type="button" class="btn btn-success" id="active">
            Hiện bình luận
        </button>
    </div>
</div>

<script src="<c:url value="/js/admin/detail-comment.js"/>"></script>