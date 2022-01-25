let drinkCode = window.location.pathname.split('/').at(-1);

function renderBills(billsList){
    let html = ``;
    $.each(billsList, function(index, item){
        html += `
        <div class="p-2" style="border-radius: 8px; border: 1px solid gray;">
            <div class="d-flex justify-content-between">
            <div>Mã đơn hàng: <span class="fw-bold">${item.code}</span></div>
            <div>Ngày tạo: <span>${moment(item.createdAt).format('HH:MM DD/MM/YYYY')}</span></div>
            </div>
            <div>Tên khách hàng: <span class="text-capitalize">${item.customerName}</span></div>
            <div>Tổng tiền: <span class="text-capitalize">${dottedMoney(item.finalMoney)}</span><sup>VNĐ</sup></div>
            <div>Đặt cọc: <span class="text-capitalize">${item.provisionalMoney}</span><sup>VNĐ</sup></div>
            <div>Nhân viên sale: <span class="text-capitalize">${item.employee?.firstName + " " + item.employee?.lastName}</span></div>
            <div>Trạng thái: <span class="text-capitalize">${item.status?.title}</span></div>
            <div>Thời gian bắt đầu: <span class="text-capitalize">${moment(item.beginDate).format('HH:MM DD/MM/YYYY')}</span></div>
        </div>
        `
    });

    return html;
}

$(document).ready(async function () {
    const response = await axios.get(`/restaurant_war_exploded/api/v1/admin/drinks/${drinkCode}`).catch(e=>e);
    if(response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    drinkDetail = response.data.result;

    $('#drink-code').val(drinkDetail.code);

    $('#drink-name').val(drinkDetail.name);

    $('#drink-status').val(drinkDetail.status ? 1 : 0);

    $('#retail-price').val(drinkDetail.price);

    $('#unit').val(drinkDetail.unit);

    $('#description').html(drinkDetail.description);
})