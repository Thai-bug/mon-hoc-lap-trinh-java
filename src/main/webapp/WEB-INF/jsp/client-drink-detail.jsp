<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 25/01/2022
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div class="container">
    <div class=" mb-3">
        Chào quý khách đến với đồ uống:   <span class="drink-name fs-3"></span>
    </div>
    <div>
        <i>Sau đây là thông tin chi tiết về đồ uống</i>
        <div>Tên đồ uống: <span class="drink-name"></span></div>
        <div>Giá đơn vị: <span id="retail-price"></span><sup>VNĐ</sup></div>
        <div>Đơn vị tính: <span id="unit"></span></div>
        <div>Mô tả</div>
    </div>


    <div>
        Danh sách các bình luận:
    </div>
    <div></div>
    <div id="drink-detail-comments"></div>
    <div class="mt-5">
        <textarea id="comment"></textarea>
        <input id="rating" name="rating" type="number" class="rating" min=1 max=5 step=1 data-size="md" >
        <div class=" d-flex justify-content-end">
            <button type="button" class="btn btn-success mt-4" id="add-comment">Thêm bình luận</button>
        </div>
    </div>

</div>

<script src="<c:url value="/js/client/drink.js"/>"></script>
