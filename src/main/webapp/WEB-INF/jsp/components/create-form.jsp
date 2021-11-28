<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 28/11/2021
  Time: 04:40
  To change this template use File | Settings | File Templates.
--%>
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
<c:url var="action" value="/admin/employee/create"/>

<c:if test="${errMsg !=null}">
    <div class="alert alert-error mb-2">
        <div class="flex-1">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="w-6 h-6 mx-2 stroke-current">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M18.364 18.364A9 9 0 005.636 5.636m12.728 12.728A9 9 0 015.636 5.636m12.728 12.728L5.636 5.636"></path>
            </svg>
            <label>${errMsg}</label>
        </div>
        <div class="flex-none">
            <button class="btn btn-sm btn-ghost mr-2 alert-button">Đóng</button>
        </div>
    </div>

</c:if>


<div class="relative flex justify-around bg-gray w-full mb-6 shadow-lg rounded p-8">
    <div class="rounded  bg-transparent " id="avatar-user">
        <img class="w-auto mx-auto h-64 object-cover object-center" src="<c:url value="/images/default-avatar.jpeg"/>"
             alt="avatar"
             style="border-radius: 50%"/>
    </div>


    <div class="w-7/12">
        <div class="text-center font-bold uppercase">Thêm thông tin nhân viên</div>
        <div>
            <form:form modelAttribute="employee" method="post" enctype="multipart/form-data" action="${action}"
                       autocomplete="off">
                <div class="form-control grid grid-cols-2 gap-4">
                    <div>
                        <label class="label inline-block">
                            <span class="label-text">Email</span>
                        </label>
                        <form:errors path="email" cssClass="text-red-500 text-sm" element="span"/>
                        <form:input path="email" placeholder="Email" cssClass="input input-bordered w-full"
                                    autocomplete="false"/>

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
                        <label class="label inline-block">
                            <span class="label-text">Họ </span>
                        </label>
                        <form:errors path="firstName" cssClass="text-red-500 text-sm" element="span"/>
                        <form:input path="firstName" placeholder="Họ"
                                    cssClass="input input-bordered w-full capitalize"/>
                    </div>

                    <div>
                        <label class="label inline-block">
                            <span class="label-text">Tên</span>
                        </label>
                        <form:errors path="lastName" cssClass="text-red-500 text-sm" element="span"/>
                        <form:input path="lastName" placeholder="Tên"
                                    cssClass="input input-bordered w-full capitalize"/>
                    </div>

                    <div class="">
                        <label class="label inline-block">
                            <span class="label-text">Giới tính</span>
                        </label>
                        <form:errors path="gender" cssClass="text-red-500 text-sm" element="span"/>
                        <form:select path="gender" cssClass="select select-bordered w-full">
                            <option selected disabled>Chọn giới tính</option>
                            <form:option value="${1}">Nam</form:option>
                            <form:option value="${2}">Nữ</form:option>
                        </form:select>
                    </div>

                    <div>
                        <label class="label inline-block">
                            <span class="label-text">Số điện thoại</span>
                        </label>
                        <form:errors path="phoneNumber" cssClass="text-red-500 text-sm" element="span"/>
                        <form:input path="phoneNumber" placeholder="Số điện thoại"
                                    cssClass="input input-bordered w-full capitalize"/>
                    </div>

                    <div class="">
                        <label class="label inline-block">
                            <span class="label-text">Chức vụ</span>
                        </label>
                        <form:errors path="role" cssClass="text-red-500 text-sm" element="span"/>
                        <form:select path="role" cssClass="select select-bordered w-full">
                            <option disabled selected>Chọn chức vụ</option>
                            <form:option value="EMPLOYEE">Nhân viên tiệc cưới</form:option>
                            <form:option value="CS">Nhân viên CSKH</form:option>
                        </form:select>
                    </div>
                    <c:if test="${fn:contains(employee.role, 'MANAGER') != true}">
                        <div class="">
                            <label class="label">
                                <span class="label-text">Người quản lý</span>
                            </label>

                            <form:select path="parent.id" cssClass="select select-bordered w-full">
                                <option selected disabled>Chọn người quản lý</option>
                                <c:forEach var="parent" items="${parents}">
                                    <form:option value="${parent.id}"
                                                 itemValue="id">${parent.firstName} ${parent.lastName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </c:if>

                    <div class="">
                        <label class="label inline-block">
                            <span class="label-text">Mật khẩu</span>
                        </label>
                        <form:errors path="password" cssClass="text-red-500 text-sm" element="span"/>
                        <form:input path="password" cssClass="input input-bordered w-full" type="password"
                                    autocomplete="false" placeholder="Nhập mật khẩu"/>
                    </div>
                    <div class="">
                        <label class="label">
                            <span class="label-text">Nhập lại mật khẩu</span>
                        </label>
                        <form:input path="confirmPassword" placeholder="Nhập lại mật khẩu"
                                    class="input input-bordered w-full" type="password"/>
                        <form:errors path="confirmPassword" cssClass="text-red-500 text-sm" element="div"/>

                    </div>

                </div>
                <div class="text-center mt-3">
                    <button class="btn btn-success w-7/12">Cập nhật</button>
                </div>

            </form:form>
        </div>
    </div>
</div>