let lobbyCode = window.location.pathname.split('/').at(-1);

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
    const container = $('#pagintaion-bills');

    const setting = {
        showPrevious: true,
        showNext: true,
        pageSize: 5,
        pageNumber: 1,
        dataSource: `/restaurant_war_exploded/api/v1/admin/bills/bills-by-lobby?lobbyCode=${lobbyCode}`,
        totalNumberLocator: (response) => {
            return 1
        },
        alias: {
            pageNumber: 'page',
            pageSize: 'limit'
        },
        locator: 'response.data',
        ajax: {
            beforeSend: function () {
                container.prev().html('Đang tải dữ liệu ...');
            }
        },
        callback: function (response, pagination) {
            container.prev().html(renderBills(response));
        }
    }
    container.pagination(setting)

    const response = await axios.get(`/restaurant_war_exploded/api/v1/admin/lobbies/${lobbyCode}`).catch(e=>e);
    if(response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    lobbyDetail = response.data.result;

    $('#lobby-code').val(lobbyDetail.code);

    $('#lobby-name').val(lobbyDetail.name);

    $('#lobby-status').val(lobbyDetail.status ? 1 : 0);

    $('#lobby-price').val(lobbyDetail.money);

    $('#lobby-tables').val(lobbyDetail.seats / 10);

    $('#description').html(lobbyDetail.description);
})