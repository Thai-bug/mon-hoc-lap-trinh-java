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

<div class="rounded  bg-transparent " id="avatar-user">
    <img class="w-auto mx-auto h-64 object-cover object-center" src="${employee.avatarLink}"
<%--            <c:if test="${employee.gender == 1}">--%>
<%--              <c:url value="/images/man.png"/>--%>
<%--            </c:if>--%>
<%--            <c:if test="${employee.gender == 2}">--%>
<%--              <c:url value="/images/woman.png"/>--%>
<%--            </c:if>--%>
" alt="avatar"
    style="border-radius: 50%"
    />
  <div class="mt-5 w-full text-center">
    <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" id="change-avatar">
      Thay đổi
    </button>
  </div>
</div>

<div class="rounded bg-transparent hidden" id="avatar-upload">
  <div class="bg-transparent rounded-lg text-center w-64">
    <label class="cursor-pointer mt-4">
      <img class="w-auto mx-auto h-64 object-cover object-center" style="border-radius: 50%" src="${employee.avatarLink}" alt="Avatar Upload" id="show-avatar" />
      <form:form modelAttribute="employee" method="post" enctype="multipart/form-data" action="${action}">
      <form:input path="avatar" type='file' cssClass="hidden" id="avatar" accept="image/*"/>
        <form:input path="id" type="hidden"/>
        <div class="mt-5 w-full text-center">
          <button type="submit"
                  class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-5/12"
                  id="update-avatar"
          disabled>
            Cập nhật
          </button>
          <button type="button" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded w-5/12" id="cancel-avatar">
            Huỷ
          </button>
        </div>

      </form:form>
    </label>
  </div>

</div>
