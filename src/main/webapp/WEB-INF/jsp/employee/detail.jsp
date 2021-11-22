<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 22/11/2021
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="text-sm text-gray-400 mb-8">Quản lý nhân viên</div>
<div class="flex justify-around h-1/2">
  <tiles:insertAttribute name="avatar" />

  <tiles:insertAttribute name="detailInfo" />
</div>
