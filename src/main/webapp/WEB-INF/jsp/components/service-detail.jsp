<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 12/12/2021
  Time: 07:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="flex flex-row justify-center">
    <div class="w-9/12  bg-gray-200 p-8 rounded-xl border-gray-300 border border-solid shadow-xl">
        <div class="text-center font-semibold text-2xl uppercase">Thông tin dịch vụ</div>
        <div class="leading-10 mt-8">
            <div class="grid grid-cols-2">
                <div>
                    <span>Tên đồ uống: </span><span
                        class="capitalize font-semibold">${service.name}</span>
                </div>

                <div>
                    <span>Giá tiền: </span><span class="font-semibold">${service.price}</span>
                </div>

<%--                <div>--%>
<%--                    <span>Đơn vị tính: </span><span--%>
<%--                        class="capitalize font-semibold">${service.unit}</span>--%>
<%--                    </span>--%>
<%--                </div>--%>

                <div>
                    <span>Trạng thái: </span>
                    <c:if test="${service.status == true}">
                        <span class="inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-white bg-green-600 rounded-full">Hoạt động</span>
                    </c:if>
                    <c:if test="${service.status == false}">
                        <span class="inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-white bg-red-600 rounded-full">Bị khoá</span>
                    </c:if>
                </div>

                <div>
                    <span>Ngày tạo: </span>
                    <span class="font-semibold">
                 <c:set var="Date" value="${service.createdAt}"/>
                 <fmt:formatDate value="${Date}" pattern="dd/MM/YYYY"/>
                </span>
                </div>
            </div>
            <div class="text-center mt-3">
                <a href="<c:url value="/admin/services/update/${id}"/>" class="btn btn-outline btn-success w-36">Cập
                    nhật</a>
            </div>
        </div>
    </div>

</div>