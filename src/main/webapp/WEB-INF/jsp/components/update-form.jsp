<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 24/11/2021
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="action" value="/admin/employee/update/${employee.id}"/>

<div class=" d-flex justify-content-between">
    <div class="rounded  bg-transparent " id="avatar-user" style="width: 30%;">
        <img class="w-auto mx-auto h-64 object-cover object-center" src="${employee.avatarLink}" alt="avatar"
             style="border-radius: 50%"/>
    </div>


    <div class="" style="width: 70%;">
        <div class="text-center fw-bold">Cập nhật thông tin nhân viên</div>
        <div>
            <form:form modelAttribute="employee" method="post" enctype="multipart/form-data" action="${action}" cssClass="form-control">
                <form:input path="id" type="hidden"/>
                <form:input path="avatarLink" type="hidden"/>

                <label for="email">email <span class="text-danger">*</span> </label>
                <form:input path="email" placeholder="Email" cssClass="form-control"
                            readonly="true"/>

                <label for="status">Trạng thái <span class="text-danger">*</span> </label>
                <form:select path="status" cssClass="form-select">
                    <form:option value="true"> Kích hoạt</form:option>
                    <form:option value="false">Vô hiệu hoá</form:option>
                </form:select>

                <label for="firstName">Họ <span class="text-danger">*</span> </label>
                <form:input path="firstName" placeholder="Họ"
                            cssClass="form-control"/>

                <label for="lastName">Tên <span class="text-danger">*</span> </label>
                <form:input path="lastName" placeholder="Họ"
                            cssClass="form-control"/>

                <label for="gender">Giới tính<span class="text-danger">*</span> </label>
                <form:select path="gender" cssClass="form-select">
                    <form:option value="1">Nam</form:option>
                    <form:option value="2">Nữ</form:option>
                </form:select>

                <label for="phoneNumber">Số điện thoại<span class="text-danger">*</span> </label>
                <form:input path="phoneNumber" placeholder="Số điện thoại"
                            cssClass="form-control"/>

                <label for="role">Chức vụ<span class="text-danger">*</span> </label>
                <form:select path="role" cssClass="form-select">
                    <form:option value="EMPLOYEE">Nhân viên</form:option>
                    <form:option value="CS">Nhân viên CSKH</form:option>
                </form:select>

                <label for="parent.id">Người quản lý<span class="text-danger">*</span> </label>

                <form:select path="parent.id" cssClass="form-select">
                    <c:forEach var="parent" items="${parents}">
                        <form:option value="${parent.id}"
                                     itemValue="id">${parent.firstName} ${parent.lastName}</form:option>
                    </c:forEach>
                </form:select>
                <div class="text-center mt-3">
                    <button class="btn btn-success w-7/12">Cập nhật</button>
                </div>

            </form:form>
        </div>
    </div>
</div>