<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 04/11/2021
  Time: 01:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="login" value="/login"/>
<nav class="bg-gray-100 rounded-md">
    <div class="max-w-6xl mx-auto px-4">
        <div class="flex justify-between">

            <div class="flex space-x-4">
                <!-- logo -->
                <div>
                    <a href="#" class="flex items-center py-5 px-2 text-gray-700 hover:text-gray-900">
                        <svg class="h-6 w-6 mr-1 text-blue-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8" />
                        </svg>
                        <span class="font-bold">Better Dev</span>
                    </a>
                </div>

                <!-- primary nav -->
                <div class="hidden md:flex items-center space-x-1">
                    <c:forEach var="cat" items="${categories}">
                        <a href=""
                           class="py-2 px-2 font-medium text-gray-500 rounded hover:bg-gray-500 hover:text-white transition duration-300">${cat.title}</a>
                    </c:forEach>
                </div>
            </div>

            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div class="hidden md:flex items-center space-x-1">
                    <a href="${login}" class="py-5 px-3">Login</a>
                </div>
            </c:if>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div class="hidden md:flex items-center space-x-1">
                    <div class="py-5 px-3">${pageContext.request.userPrincipal.name}</div>
                </div>
            </c:if>

            <!-- mobile button goes here -->
            <div class="md:hidden flex items-center">
                <button class="mobile-menu-button">
                    <svg class="w-6 h-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                    </svg>
                </button>
            </div>

        </div>
    </div>

    <!-- mobile menu -->
    <div class="mobile-menu hidden md:hidden">
        <c:forEach var="cat" items="${categories}">
            <a href=""
               class="py-2 px-2 font-medium text-gray-500 rounded hover:bg-gray-500 hover:text-white transition duration-300">${cat.title}</a>
        </c:forEach>
    </div>
</nav>