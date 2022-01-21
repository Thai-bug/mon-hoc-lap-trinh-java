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

<div class="container">
    <div class="">
        <div class="text-center">
            <img class=""
                 src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/avatar.svg" width="100">
        </div>
        <h2 class="text-center">Nhà Hàng Ăn Cay</h2>
        <div class="d-flex justify-content-center">
        <form class="" action="${action}" method="POST" autocomplete="off">
            <div class=" ">
                <div class="form-field form-controller">
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

            <button type="submit" class="btn">Đăng nhập</button>
        </form>
        </div>
    </div>
</div>
