let keyword = "";
let limit = 10;

function renderLobbiesList(data) {
    let html = `<div class="row gx-5">`
    $.each(data, function (index, item) {
        html += `
        <div style=" min-height: 50px; padding: 15px" class="col-6">
                    <div  style="font-weight: 600; text-transform: uppercase; ">
                    <a style="text-decoration: none;" href="/restaurant_war_exploded/lobbies/${item.code}">${item.name}</a></div>
                    <div>Giá thuê: ${dottedMoney(item.money)}<span></span><sup>VNĐ</sup></div>
                    <div>Mô tả:</div><br/>
                </div>
`
    });

    html+=`</div>`

    return html;
}

$(document).ready(function () {
    const container = $('#pagintaion-lobbies');

    const setting = {
        showPrevious: true,
        showNext: true,
        pageSize: limit,
        pageNumber: 1,
        dataSource: `/restaurant_war_exploded/api/v1/lobbies?kw=${keyword}`,
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
            container.prev().html(renderLobbiesList(response));
        }
    }
    container.pagination(setting)

    $('#keyword').on('keypress', function (e) {
        if (e.which === 13) {
            keyword=$('#keyword').val();
            setting.dataSource = `/restaurant_war_exploded/api/v1/lobbies?kw=${keyword}`
            container.pagination(setting)
            container.pagination('go', 1);
        }
    });
})