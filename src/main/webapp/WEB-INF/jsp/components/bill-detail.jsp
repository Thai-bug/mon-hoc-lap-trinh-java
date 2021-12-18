<%--
  Created by IntelliJ IDEA.
  User: thai-bug
  Date: 12/12/2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="main" class="hidden bg-gray-200 rounded-xl m-16 leading-loose p-4">
    <div class="text-center uppercase text-blue-500 font-bold text-3xl">
        Thông tin đơn hàng
    </div>
    <div class="grid grid-cols-3 gap-x-16 gap-y-4">
        <div>
            <div>
                <div class="text-sm mb-1 mt-2">Mã đơn hàng</div>
                <input id="code" class="border-gray-400 border rounded-xl w-full" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Họ và tên khách hàng:</div>
                <input id="customer-name" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tên bữa tiệc:</div>
                <input id="name" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>
        </div>
        <div>

            <div>
                <div class="text-sm mb-1 mt-2">Trạng thái:</div>
                <input id="bill" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tên sánh:</div>
                <input id="lobby" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Thời gian bắt đầu:</div>
                <input id="beginDate" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>
        </div>

        <div>

            <div>
                <div class="text-sm mb-1 mt-2">Nhân viên sale:</div>
                <input id="employee" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Đặt cọc:</div>
                <input id="deposit" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>

            <div>
                <div class="text-sm mb-1 mt-2">Tổng tiền:</div>
                <input id="total" class="border-gray-400 border rounded-xl w-full capitalize" disabled/>
            </div>
        </div>

    </div>

    <div class="mt-5 grid grid-cols-3 gap-8">
        <div class="p-2">
            <h1 class="text-center leading-loose">THỰC ĐƠN</h1>
            <div class="w-full rounded-lg border-gray-900 border min-height-200 max-height-200" id="food">
            </div>
        </div>

        <div class="p-2">
            <h1 class="text-center leading-loose">THỨC UỐNG</h1>
            <div class="w-full rounded-lg border-gray-900 border min-height-200 max-height-200" id="drink">
            </div>
        </div>

        <div class="p-2">
            <h1 class="text-center leading-loose">DỊCH VỤ</h1>
            <div class="w-full rounded-lg border-gray-900 border min-height-200 max-height-200" id="service">
            </div>
        </div>
    </div>

    <div>
        <a href="<c:url value="/admin/bills/update?id=${pageContext.request.getParameter('id')}" />">Cập nhật đơn hàng</a>
    </div>
</div>

<script>
    let data;
    const id = new URLSearchParams(location.search).get("id")
    const callApi = async (identity) => {
        const id = identity
        const urlString = `/restaurant_war_exploded/api/v1/admin/bills/detail/` + id
        const response = await fetch(urlString);
        data = await response.json();
        if (response.status === 200) {
            passData(data);
            $('#main').removeClass('hidden')
        }
    }

    const passData = (orderInfo) => {
        console.log({orderInfo})
        $('#code').val(orderInfo.code);

        $('#customer-name').val(orderInfo.customerName);

        $('#name').val(orderInfo.name);

        const employeeName = orderInfo.employee.firstName + ' ' + orderInfo.employee.lastName
        $('#employee').val(employeeName);

        $('#lobby').val(orderInfo.lobby.name);

        $('#beginDate').val(moment(orderInfo.beginDate).format('DD/MM/YYYY HH:mm'));

        $('#bill').val(orderInfo.status.title);

        $('#deposit').val(orderInfo.provisionalMoney.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));

        $('#total').val(orderInfo.finalMoney.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));

        showFoods(orderInfo.foodList)

        showDrinks(orderInfo.drinkList);

        showService(orderInfo.serviceList);
    }

    const showFoods = (foods) => {
        foods.map((item, index) => {
            if (index % 2 === 0)
                return $("#food").append(
                    '<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose ">' +
                    //  '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current">'+
                    // '   <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>' +
                    //  '</svg>'+
                    item.name +
                    '</div>'
                )

            return $("#food").append(
                '<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose ">' +
                // '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current">'+
                // '   <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>' +
                // '</svg>'+
                item.name +
                '</div>'
            )
        })
    }

    const showDrinks = (drinks) => {
        drinks.map((item, index) => {
            if (index % 2 === 0)
                return $("#drink").append(
                    '<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose ">' +
                    // '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current">'+
                    // '   <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>' +
                    // '</svg>'+
                    item.name +
                    '</div>'
                )

            return $("#drink").append(
                '<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose ">' +
                // '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current">'+
                // '   <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>' +
                // '</svg>'+
                item.name +
                '</div>'
            )
        })
    }

    const showService = (services) => {
        services.map((item, index) => {
            if (index % 2 === 0)
                return $("#service").append(
                    '<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose ">' +
                    // '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current">'+
                    // '   <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>' +
                    // '</svg>'+
                    item.name +
                    '</div>'
                )

            return $("#service").append(
                '<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose ">' +
                // '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current">'+
                // '   <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>' +
                // '</svg>'+
                item.name +
                '</div>'
            )
        })
    }

    $(document).ready(function () {
        callApi(id);
    })

</script>

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
