let keyword = "";
let limit = 10;

function renderServicesList(data) {
    let html = `<div class="row gx-5">`
    $.each(data, function (index, item) {
        html += `
        <div style=" min-height: 50px; padding: 15px" class="col-6">
                    <div  style="font-weight: 600; text-transform: uppercase; ">
                    <a style="text-decoration: none;" href="/restaurant_war_exploded/services/${item.code}">${item.name}</a></div>
                    <div>Giá đơn vị: ${dottedMoney(item.price)}<span></span><sup>VNĐ</sup></div>
                </div>
`
    });

    html+=`</div>`

    return html;
}

$(document).ready(function () {
    const container = $('#pagintaion-services');

    const setting = {
        showPrevious: true,
        showNext: true,
        pageSize: limit,
        pageNumber: 1,
        dataSource: `/restaurant_war_exploded/api/v1/services?kw=${keyword}`,
        totalNumberLocator: (response) => {
            return response.total
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
            container.prev().html(renderServicesList(response));
        }
    }
    container.pagination(setting)

    $('#keyword').on('keypress', function (e) {
        if (e.which === 13) {
            keyword=$('#keyword').val();
            setting.dataSource = `/restaurant_war_exploded/api/v1/services?kw=${keyword}`
            container.pagination(setting)
            container.pagination('go', 1);
        }
    });
})