<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 24/01/2022
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div class="container">
    <div class=" mb-3">
        Chào quý khách đến với sảnh:   <span class="lobby-name fs-3"></span>
    </div>
    <div>
        <i>Sau đây là thông tin chi tiết về sảnh</i>
        <div>Tên sảnh: <span class="lobby-name"></span></div>
        <div>Giá thuê: <span id="retail-price"></span><sup>VNĐ</sup>/4 giờ</div>
        <div>Số bàn tối đa: <span id="max-tables"></span> bàn</div>
        <div class="btn btn-link" data-bs-toggle="modal" data-bs-target="#exampleModal">Mô tả</div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Mô tả sảnh</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="description">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div>
        Danh sách các bình luận:
    </div>
    <div></div>
    <div id="lobby-detail-comments"></div>
    <div class="mt-5">
        <textarea id="comment"></textarea>
        <input id="rating" name="rating" type="number" class="rating" min=1 max=5 step=1 data-size="md" >
        <div class=" d-flex justify-content-end">
            <button type="button" class="btn btn-success mt-4" id="add-comment">Thêm bình luận</button>
        </div>
    </div>

</div>

<script src="<c:url value="/js/client/lobby.js"/>"></script>