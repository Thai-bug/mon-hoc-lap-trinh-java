<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 22/11/2021
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div style="">
    <div class="text-center fw-bold fs-2 text-uppercase">Thông tin nhân viên</div>
    <div class="leading-10 mt-8">
        <div class="grid grid-cols-2">
            <div>
                <span>Họ và tên: </span><span
                    class="capitalize font-semibold">${employee.firstName} ${employee.lastName}</span>
            </div>

            <div>
                <span>Chức vụ: </span><span class="font-semibold">${employee.role}</span>
            </div>

            <div>
                <span>Giới tính: </span><span
                    class="capitalize font-semibold">
          <c:if test="${employee.gender == 1}">Nam</c:if>
           <c:if test="${employee.gender == 2}">Nữ</c:if>
         </span>
            </div>

            <div>
                <span>Email: </span><span class="font-semibold">${employee.email}</span>
            </div>

            <div>
                <span>Số điện thoại: </span><span class="font-semibold">${employee.phoneNumber}</span>
            </div>

            <div>
                <span>Người quản lý: </span>
                <span class="font-semibold">
                 <c:if test="${employee.id == employee.parent.id}">
                     N/A
                 </c:if>
                 <c:if test="${employee.id != employee.parent.id}">
                     ${employee.parent.firstName} ${employee.parent.lastName}
                 </c:if>
                </span>
            </div>

            <div>
                <span>Trạng thái: </span>
                <c:if test="${employee.status == true}">
                    <span class="inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-white bg-green-600 rounded-full">Hoạt động</span>
                </c:if>
                <c:if test="${employee.status == false}">
                    <span class="inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-white bg-red-600 rounded-full">Bị khoá</span>
                </c:if>
            </div>

            <div>
                <span>Ngày tạo: </span>
                <span class="font-semibold">
                 <c:set var="Date" value="${employee.createdAt}"/>
                 <fmt:formatDate value="${Date}" pattern="dd/MM/YYYY"/>
                </span>
            </div>
        </div>
        <div class="text-center mt-3">
            <a href="<c:url value="/admin/employee/update/${employee.id}"/>" class="btn btn-outline btn-success w-36">Cập
                nhật</a>
            <button class="btn btn-outline btn-warning w-36">Đổi mật khẩu</button>
        </div>
    </div>
</div>
