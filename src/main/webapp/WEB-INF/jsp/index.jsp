<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 03/11/2021
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<div class="relative h-16 my-5">
    <div class="absolute top-0 right-0">
        <form action="">
        <div class="relative flex-wrap items-stretch mb-3 inline-block">
  <span class=" z-10 h-full leading-snug font-normal absolute text-center text-gray-400 absolute bg-transparent rounded text-base items-center justify-center w-8 pl-2 py-1">
    <i class="fas fa-search"></i>
  </span>
            <input type="text" placeholder="Tìm kiếm" class="
      px-2
      py-1
      placeholder-gray-400
      text-gray-600
      relative
      bg-white bg-white
      rounded
      text-sm
      border border-gray-400
      outline-none
      focus:outline-none focus:ring
      w-full
      pl-10
    " name="kw"
            />
        </div>
<%--        <button type="submit" class="border-black border border-solid rounded px-2 hover:bg-gray-500 hover:text-white">--%>
<%--            Tìm kiếm--%>
<%--        </button>--%>
        </form>
    </div>

</div>

<div class="w-full bg-gray-800">
    <section class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-4 py-12">
        <div class="text-center pb-12">
            <h2 class="text-base font-bold text-indigo-600">
                We have the best equipment in the market
            </h2>
            <h1 class="font-bold text-3xl md:text-4xl lg:text-5xl font-heading text-white">
                Check our awesome team memwhite
            </h1>
        </div>
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach var="product" items="${products}">
                <div class="w-full bg-gray-900 rounded-lg sahdow-lg p-12 flex flex-col justify-center items-center">
                    <div class="mb-8">
                        <img class=" object-cover  h-36" src="${product.image}"
                             alt="photo">
                    </div>
                    <div class="text-center">
                        <p class="text-xl text-white font-bold mb-2">${product.title}</p>
                        <p class="text-base text-gray-400 font-normal">Software Engineer</p>
                    </div>
                </div>
            </c:forEach>

        </div>
    </section>
</div>