<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 02/12/2021
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="fs-1 text text-center">Thông tin sảnh tiệc</div>
<div class="container">
    <div class="row">
        <div class="col"><span>Tên sảnh: </span><span
                class="capitalize font-semibold">${lobby.name}</span></div>
        <div class="col">
            <span>Giá tiền: </span><span class="font-semibold">${lobby.money}</span>
        </div>
    </div>
    <div class="row">
        <div class="col"><span>Số lượng tối đa: </span><span
                class="capitalize font-semibold">${lobby.seats}</span>
            </span>
        </div>
        <div class="col">
            <span>Trạng thái: </span>
            <c:if test="${lobby.status == true}">
                <span>Hoạt động</span>
            </c:if>
            <c:if test="${lobby.status == false}">
                <span>Bị khoá</span>
            </c:if>
        </div>
    </div>
    <div>
        <span>Ngày tạo: </span>
        <span class="font-semibold">
                     <c:set var="Date" value="${lobby.createdAt}"/>
                     <fmt:formatDate value="${Date}" pattern="dd/MM/YYYY"/>
                    </span>
    </div>
    <div class="text-center mt-3">
        <a href="<c:url value="/admin/lobby/update/${lobby.code}"/>" class="btn btn-outline btn-success w-36">Cập
            nhật</a>
    </div>
</div>