<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 14/11/2021
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/login" var="action"/>

<div class="min-h-full flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8 my-auto px-16">
        <div class="flex font-bold justify-center">
            <img class="h-20 w-20"
                 src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/avatar.svg">
        </div>
        <h2 class="text-3xl text-center text-gray-700 mb-4">Nhà Hàng Ăn Cay</h2>
        <form class="mt-8 space-y-6" action="${action}" method="POST" autocomplete="off">
            <div class="relative  my-5 py-1 ">
                <div class="form-field">
                    <input  path="email" type="text" id="email" name="phoneNumber"
                           class="form-input w-full outline-none focus:border-green-300 no-placeholder" placeholder="a"
                           cssStyle="box-shadow: none" autocomplete="false"/>
                    <label for="email" class="form-label">
                        Email
                    </label>
                </div>
            </div>

            <div class="relative  my-5 py-1 ">
                <div class="form-field">
                    <input id="password" type="password" name="password"
                           class="form-input w-full outline-none focus:border-green-300 no-placeholder" placeholder="a"
                           cssStyle="box-shadow: none" autocomplete="false"/>
                    <label for="password" class="form-label">
                        Mật khẩu
                    </label>
                </div>
            </div>

            <button type="submit">Đăng nhập</button>
        </form>
    </div>
</div>
