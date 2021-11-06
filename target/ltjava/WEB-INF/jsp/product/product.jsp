<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 06/11/2021
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Quản lý sản phẩm</h1>
<form:form method="post" modelAttribute="${product}">
    <div class="py-20 bg-gray-300 px-2 flex justify-between">
        <div class="w-9/12  mx-auto bg-white rounded-lg overflow-hidden ">
            <div class="md:flex h-68">
                <div class="w-full">
                    <div class="p-3">
                        <div class="mb-2"><span>Hình ảnh sản phẩm</span>
                            <div class="relative h-40 rounded-lg border-dashed border-2 border-gray-200 bg-white flex justify-center items-center hover:cursor-pointer">
                                <div class="absolute">
                                    <div class="flex flex-col items-center "><i
                                            class="fa fa-cloud-upload fa-3x text-gray-200"></i> <span
                                            class="block text-gray-400 font-normal">Đính kèm hình ảnh sản phẩm tại đây</span> <span
                                            class="block text-gray-400 font-normal">hoặc</span> <span
                                            class="block text-blue-400 font-normal">Dẫn tới hình ảnh</span></div>
                                </div>
                                <input type="file" class="h-full w-full opacity-0" id="imgInp" name="" accept="image/*">
                            </div>
                        </div>
<%--                        <div class="mt-4 text-center pb-2">--%>
<%--                            <button class="w-full h-12 text-lg w-32 bg-blue-600 rounded text-white hover:bg-blue-700">--%>
<%--                                Create--%>
<%--                            </button>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </div>
        </div>
<%--        <div class="max-w-sm rounded overflow-hidden shadow-lg p-10 hidden " id="uploaded-image">--%>
<%--            <img   class=""/>--%>
<%--        </div>--%>
        <div class=" hidden mr-9" id="uploaded-image">
            <img class= "w-40" src="#" id="image"/>
        </div>
    </div>
</form:form>

<script>
    const imgInp = document.getElementById('imgInp');

    imgInp.onchange = evt => {
        const [file] = imgInp.files
        if (file) {
            image.src = URL.createObjectURL(file);
            const imgContainer = document.getElementById('uploaded-image');
            imgContainer.classList.remove('hidden');
        }
    }
</script>