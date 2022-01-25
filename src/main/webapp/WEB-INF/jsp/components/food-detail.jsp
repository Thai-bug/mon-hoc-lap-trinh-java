<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 08/12/2021
  Time: 02:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="fs-1 text text-center">Thông tin thức ăn</div>

<div class="container">

    <div class="form-control position-relative">
        <label for="food-code">Mã thức ăn</label>
        <input type="text" id="food-code" class="form-control" disabled>

        <label for="food-name">Tên thức ăn</label>
        <input type="text" id="food-name" class="form-control" aria-describedby="passwordHelpBlock" disabled>

        <label for="food-status">Trạng thái  </label>
        <select class="form-select" id="food-status" disabled>
            <option selected disabled>Chọn trạng thức ăn</option>
            <option value="1">Đang hoạt động</option>
            <option value="0">Không hoạt động</option>
        </select>

        <label for="retail-price">Đơn vị tính  </label>
        <input type="text" id="retail-price" class="form-control" disabled>

        <label for="unit">Đơn vị tính  </label>
        <input type="text" id="unit" class="form-control" disabled>

        <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Mô tả
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Mô tả thức ăn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="description">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <div class=" text-center mt-2">
            <a href="<c:url value="/admin/foods/update/${food.code}"/>" class="btn btn-outline btn-success w-36">Cập
                nhật</a>
        </div>
    </div>

    <div class="mb-2 mt-2"></div>
    <div id="pagintaion-bills"></div>
</div>

<script src="<c:url value="/js/admin/detail-food.js"/>"></script>
