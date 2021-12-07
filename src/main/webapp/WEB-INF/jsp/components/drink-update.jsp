<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 08/12/2021
  Time: 03:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="action" value="/admin/drinks/update/${drink.id}"/>

<div class="flex flex-row justify-center">
    <div class="w-9/12  bg-gray-200 p-8 rounded-xl border-gray-300 border border-solid shadow-xl">
        <div class="text-center font-semibold text-2xl uppercase">Thông tin sảnh tiệc</div>
        <form:form modelAttribute="drink" method="post" enctype="multipart/form-data" action="${action}">
            <form:input path="id" type="hidden"/>
            <%--            <form:input path="createdAt" type="hidden"/>--%>

            <div class="form-control grid grid-cols-2 gap-4">
                <div>
                    <label class="label">
                        <span class="label-text">Tên đồ uồng</span>
                    </label>
                    <form:input path="name" placeholder="Tên đồ uống" cssClass="input input-bordered w-full"/>
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
                        <span class="label-text">Đơn vị tính </span>
                    </label>
                    <form:input path="unit" placeholder="Đơn vị tính"
                                cssClass="input input-bordered w-full capitalize"/>
                </div>

                <div>
                    <label class="label">
                        <span class="label-text">Giá tiền</span>
                    </label>
                    <form:input path="price" placeholder="Giá tiền"
                                cssClass="input input-bordered w-full capitalize"/>
                </div>

            </div>
            <div class="text-center mt-3">
                <button class="btn btn-success w-7/12">Cập nhật</button>
            </div>

        </form:form>
    </div>
</div>

</div>
