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
<%--<div class="h-screen flex justify-center items-center">--%>
<%--    <div class="bg-white rounded-lg w-5/12 sm:w-full px-16 py-16">--%>
<%--        <form>--%>
<%--            <div class="flex font-bold justify-center">--%>
<%--                <img class="h-20 w-20"--%>
<%--                     src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/avatar.svg">--%>
<%--            </div>--%>
<%--            <h2 class="text-3xl text-center text-gray-700 mb-4">Login Form</h2>--%>
<%--            <div class="input-div border-b-2 relative grid my-5 py-1 focus:outline-none"--%>
<%--                 style="grid-template-columns: 7% 93%;">--%>
<%--                <div class="i">--%>
<%--                    <i class="fas fa-user"></i>--%>
<%--                </div>--%>
<%--                <div class="div">--%>
<%--                    <h5>Username</h5>--%>
<%--                    <input type="text" class="absolute w-full h-full py-2 px-3 outline-none inset-0 text-gray-700"--%>
<%--                           style="background:none;">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="input-div border-b-2 relative grid my-5 py-1 focus:outline-none"--%>
<%--                 style="grid-template-columns: 7% 93%;">--%>
<%--                <div class="i">--%>
<%--                    <i class="fas fa-lock"></i>--%>
<%--                </div>--%>
<%--                <div class="div">--%>
<%--                    <h5>Password</h5>--%>
<%--                    <input type="password"--%>
<%--                           class="absolute w-full h-full py-2 px-3 outline-none inset-0 text-gray-700"--%>
<%--                           style="background:none;">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <a href="#" class="text-xs text-green-400 hover:text-green-500 float-right mb-4">Forgot Password?</a>--%>
<%--            <button type="submit"--%>
<%--                    class="w-full py-2 rounded-full bg-green-600 text-gray-100  focus:outline-none">Button</button>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="min-h-full flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8 my-auto px-16">
        <div class="flex font-bold justify-center">
            <img class="h-20 w-20"
                 src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/avatar.svg">
        </div>
        <h2 class="text-3xl text-center text-gray-700 mb-4">Nhà Hàng Ăn Cay</h2>
        <form:form class="mt-8 space-y-6" action="${action}" modelAttribute="user" method="POST" autocomplete="off">
            <div class="relative  my-5 py-1 ">
                <div class="form-field">
                    <form:input path="phoneNumber" type="text" id="phoneNumber"
                                class="form-input w-full outline-none focus:border-green-300" placeholder="a"
                                cssStyle="box-shadow: none" autocomplete="false"/>
                    <label for="phoneNumber" class="form-label">
                        Số điện thoại
                    </label>
                </div>
                <form:errors path="phoneNumber" cssClass="text-red-500" />
            </div>

            <div class="relative  my-5 py-1 ">
                <div class="form-field">
                    <form:input path="password" type="password" id="phoneNumber"
                                class="form-input w-full outline-none focus:border-green-300" placeholder="a"
                                cssStyle="box-shadow: none" autocomplete="false"/>
                    <label for="password" class="form-label">
                        Mật khẩu
                    </label>
                </div>
            </div>

            <button>Đăng nhập</button>
        </form:form>
    </div>
</div>
