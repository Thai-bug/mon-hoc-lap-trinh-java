<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 17/11/2021
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
${_csrf.token}${_csrf.parameterName}

        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
            <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline">Menu</span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                    <li>
                        <a href="<c:url value="/general"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Tổng quan</span></a>
                    </li>

                    <li>
                        <a href="<c:url value="/admin/employees"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Quản lý nhân viên</span></a>
                    </li>

                    <li>
                        <a href="<c:url value="/admin/lobbies"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Quản lý sảnh</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/drinks"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span
                                class="ms-1 d-none d-sm-inline">Quản lý đồ uống</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/food"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span
                                class="ms-1 d-none d-sm-inline">Quản lý thức ăn</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/services"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span
                                class="ms-1 d-none d-sm-inline">Quản lý dịch vụ</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/bills"/>" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Quản lý đơn hàng</span></a>
                    </li>

                </ul>
                <hr>
                <div class="dropdown pb-4">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
                       id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30"
                             class="rounded-circle">
                        <span class="d-none d-sm-inline mx-1">${pageContext.request.userPrincipal.name}</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="#">New project...</a></li>
                        <li><a class="dropdown-item" href="#">Settings</a></li>
                        <li><a class="dropdown-item" href="#">Profile</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item"href="<c:url value="/logout" /> ">Đăng xuất</a></li>
                    </ul>
                </div>
            </div>
        </div>

<script>
</script>