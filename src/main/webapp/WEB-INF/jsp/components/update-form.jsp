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

<div class="relative flex justify-around bg-gray w-full mb-6 shadow-lg rounded p-8">
    <div class="rounded  bg-transparent " id="avatar-user">
        <img class="w-auto mx-auto h-64 object-cover object-center" src="${employee.avatarLink}" alt="avatar"
             style="border-radius: 50%"/>
    </div>


    <div class="w-7/12">
        <div class="text-center font-bold uppercase">Cập nhật thông tin nhân viên</div>
        <div>
            <form:form modelAttribute="employee" method="post" enctype="multipart/form-data" action="${action}">
                <form:input path="id" type="hidden"/>
                <form:input path="avatarLink" type="hidden"/>
                <%--                <form:input path="createdAt" type="hidden"/>--%>

                <div class="form-control grid grid-cols-2 gap-4">
                    <div>
                        <label class="label">
                            <span class="label-text">Email</span>
                        </label>
                        <form:input path="email" placeholder="Email" cssClass="input input-bordered w-full"
                                    readonly="true"/>
                    </div>

                    <div class="">
                        <label class="label">
                            <span class="label-text">Trạng thái</span>
                        </label>
                        <form:select path="status" cssClass="select select-bordered status-select w-full">
                            <form:option value="true"> Kích hoạt</form:option>
                            <form:option value="false">Vô hiệu hoá</form:option>
                        </form:select>
                    </div>

                    <div>
                        <label class="label">
                            <span class="label-text">Họ </span>
                        </label>
                        <form:input path="firstName" placeholder="Họ"
                                    cssClass="input input-bordered w-full capitalize"/>
                    </div>

                    <div>
                        <label class="label">
                            <span class="label-text">Tên</span>
                        </label>
                        <form:input path="lastName" placeholder="Tên"
                                    cssClass="input input-bordered w-full capitalize"/>
                    </div>

                    <div class="">
                        <label class="label">
                            <span class="label-text">Giới tính</span>
                        </label>
                        <form:select path="gender" cssClass="select select-bordered w-full">
                            <form:option value="1">Nam</form:option>
                            <form:option value="2">Nữ</form:option>
                        </form:select>
                    </div>

                    <div>
                        <label class="label">
                            <span class="label-text">Số điện thoại</span>
                        </label>
                        <form:input path="phoneNumber" placeholder="Số điện thoại"
                                    cssClass="input input-bordered w-full capitalize"/>
                    </div>

                    <div class="">
                        <label class="label">
                            <span class="label-text">Chức vụ</span>
                        </label>
                        <form:select path="role" cssClass="select select-bordered w-full">
                            <form:option value="EMPLOYEE">Nhân viên</form:option>
                            <form:option value="CS">Nhân viên CSKH</form:option>
                        </form:select>
                    </div>
                    <c:if test="${fn:contains(employee.role, 'MANAGER') != true}">
                        <div class="">
                            <label class="label">
                                <span class="label-text">Người quản lý</span>
                            </label>

                            <form:select path="parent.id" cssClass="select select-bordered w-full">
                                <c:forEach var="parent" items="${parents}">
                                    <form:option value="${parent.id}"
                                                 itemValue="id">${parent.firstName} ${parent.lastName}</form:option>
                                </c:forEach>
                            </form:select>

                        </div>
                    </c:if>

                    <div class="">
                        <form:input path="password" cssClass="select select-bordered w-full" type="hidden"/>
                    </div>

                </div>
                <div class="text-center mt-3">
                    <button class="btn btn-success w-7/12">Cập nhật</button>
                </div>

            </form:form>
        </div>
    </div>
</div>