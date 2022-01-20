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
<div class="container">
    <div class="row">
        <div class="col"><span>Tên đồ uống: </span><span
                class="capitalize font-semibold">${drink.name}</span></div>
        <div class="col">
            <span>Giá tiền: </span><span class="font-semibold">${drink.price}</span> VNĐ
        </div>
    </div>

    <div class="row">
        <div class="col">
            <span>Đơn vị tính: </span><span
                class="capitalize font-semibold">${drink.unit}</span>
            </span>
        </div>

        <div class="col">
            <span>Trạng thái: </span>
            <c:if test="${drink.status == true}">
                <span >Hoạt động</span>
            </c:if>
            <c:if test="${drink.status == false}">
                <span >Bị khoá</span>
            </c:if>
        </div>
    </div>
    <div>
        <span>Ngày tạo: </span>
        <span class="font-semibold">
                 <c:set var="Date" value="${drink.createdAt}"/>
                 <fmt:formatDate value="${Date}" pattern="dd/MM/YYYY"/>
                </span>
        <div class="text-center mt-3">
            <a href="<c:url value="/admin/drinks/update/${id}"/>" class="btn btn-outline btn-success w-36">Cập
                nhật</a>
        </div>
    </div>
</div>
