<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 22/11/2021
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/employee/update-avatar" var="action"/>

<div style="width: 40%;">

    <div class="" id="avatar-user">
        <img class="" src="${employee.avatarLink}" alt="avatar"
             style="border-radius: 50%"
        />
        <div class="mt-5 w-full">
            <button type="button" class="btn btn-info" id="change-avatar" style="margin-left: 80px" ;>
                Thay đổi
            </button>
        </div>
    </div>

    <div class=" d-none" id="avatar-upload">
        <div class="">
            <label class="">
                <img class="" style="border-radius: 50%"
                     src="${employee.avatarLink}" alt="Avatar Upload" id="show-avatar"/>
                <form:form modelAttribute="employee" method="post" enctype="multipart/form-data" action="${action}">
                    <form:input path="avatar" type='file' cssClass="hidden" id="avatar" accept="image/*" cssStyle="display: none;"/>
                    <form:input path="id" type="hidden"/>
                    <div class="mt-5 text-center">
                        <button type="submit"
                                class="btn btn-secondary"
                                id="update-avatar"
                                disabled>
                            Cập nhật
                        </button>
                        <button type="button"
                                class="btn btn-danger"
                                id="cancel-avatar">
                            Huỷ
                        </button>
                    </div>

                </form:form>
            </label>
        </div>

    </div>

</div>
