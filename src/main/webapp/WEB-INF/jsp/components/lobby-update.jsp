<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 04/12/2021
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="action" value="/admin/lobby/update/${lobby.id}"/>


<div class="fs-1 text text-center">Thông tin sảnh tiệc</div>

<div class="container">
<form:form modelAttribute="lobby" method="post" enctype="multipart/form-data" action="${action}">
    <form:input path="id" type="hidden"/>
    <div class="row">
        <div class="col">
            <label class="label">
                <span class="label-text">Tên sảnh</span>
            </label> <br />
            <form:input path="name" placeholder="Tên sảnh" cssClass="input input-bordered w-full"
                        readonly="true"/>
        </div>
        <div class="col">
            <label class="label">
                <span class="label-text">Trạng thái</span>
            </label>
            <br />
            <form:select path="status" cssClass="select select-bordered status-select w-full">
                <form:option value="true"> Kích hoạt</form:option>
                <form:option value="false">Vô hiệu hoá</form:option>
            </form:select>
        </div>
    </div>
    <div class="row">

        <div class="col">
            <label class="label">
                <span class="label-text">Số lượng tối đa </span>
            </label>
            <br />
            <form:input path="seats" placeholder="Số lượng"
                        cssClass="input input-bordered w-full capitalize"/>
        </div>

        <div class="col">
            <label class="label">
                <span class="label-text">Giá tiền</span>
            </label>
            <br />
            <form:input path="money" placeholder="Giá tiền"
                        cssClass="input input-bordered w-full capitalize"/>
        </div>

    </div>
    <div class="text-center mt-3">

        <button class="btn btn-success w-7/12">Cập nhật</button>
    </div>
</form:form>
</div>
