<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 12/12/2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="main" class=" bg-gray-200 rounded-xl m-16 leading-loose p-4">
    <div class="text-center uppercase text-blue-500 font-bold text-3xl">
        Thông tin đơn hàng
    </div>
    <div class="row">
        <div class="col-4">
            <div>
                <div class="text-sm mb-1 mt-2">Mã đơn hàng</div>
                <input id="code" class="form-control" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Họ và tên khách hàng:</div>
                <input id="customer-name" class="form-control"/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Loại tiệc:</div>
                <select id="type" class="form-control">
                    <option disabled></option>
                </select>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tên bữa tiệc:</div>
                <input id="name" class="form-control"/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tổng bàn:</div>
                <input id="tables-bill" class="form-control"/>
            </div>
        </div>

        <div class="col-4">
            <div>
                <div class="text-sm mb-1 mt-2">Trạng thái:</div>
                <input id="status" class="form-control" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Thời gian bắt đầu:</div>
                <input id="beginDate" class="datetimepicker form-control" autocomplete="off"  placeholder="Chọn ngày">
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Thời gian kết thúc:</div>
                <input id="endDate" class="datetimepicker form-control" autocomplete="off" placeholder="Chọn ngày" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tên sảnh:</div>
                <select id="lobby-select" class=""><option disabled></option></select>
            </div>
        </div>

        <div class="col-4">
            <div>
                <div class="text-sm mb-1 mt-2">Nhân viên sale:</div>
                <input id="employee" class="form-control" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Đặt cọc:</div>
                <input id="deposit" class="form-control" value="0" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tổng tiền:</div>
                <input id="total" class="form-control" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Đặt cọc trước:</div>
                <input type="checkbox" id="pre-order" class=""/>
            </div>

        </div>
    </div>

    <div class="row">
        <div class="p-2 col-4">
            <h1 class="text-center leading-loose">THỰC ĐƠN</h1>
            <div class="mb-3">
                <select id="food-list" class="w-full">
                <option disabled></option></select>
            </div>
            <div class="w-full rounded-lg border-gray-900 border min-height-200 max-height-200" id="food">
            </div>
        </div>

        <div class="p-2 col-4">
            <h1 class="text-center leading-loose">THỨC UỐNG</h1>
            <div class="mb-3">
                <select id="drink-list" class="w-full">
                <option disabled></option></select>
            </div>
            <div class="w-full rounded-lg border-gray-900 border min-height-200 max-height-200" id="drink">
            </div>
        </div>

        <div class="p-2 col-4">
            <h1 class="text-center leading-loose">DỊCH VỤ</h1>
            <div class="mb-3">
                <select id="service-list" class="w-full">
                <option disabled></option></select>
            </div>
            <div class="w-full rounded-lg border-gray-900 border min-height-200 max-height-200" id="service">
            </div>
        </div>
    </div>

    <div class="text-center">
        <button type="button" class="btn btn-success" id="create-bill" onclick="createOrder()">Tạo đơn
            hàng</button>
    </div>
</div>

<script src="<c:url value="/js/create-order.js"/>"></script>

<style>
    input {
        padding-left: 10px;
    }

    .min-height-200 {
        min-height: 200px !important;
    }

    .max-height-200 {
        max-height: 200px !important;
    }
</style>

