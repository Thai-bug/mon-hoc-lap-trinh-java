let drinkDetail = {};
let editor;
let drinkCode = window.location.pathname.split('/').at(-1);

ClassicEditor
    .create( document.querySelector( '#comment' ),{
        placeholder: 'Thêm bình luận tại đây'
    })
    .then(newEditor=>{
        editor = newEditor;
    })
    .catch( error => {
        console.error( error );
    } );

$('#add-comment').on('click', async function(){
    if(!editor.getData() || editor.getData() === '')
        return notify('Vui lòng nhập nội dung', 'warning')
    $(this).attr('disabled', true);
    const response = await axios.post('/restaurant_war_exploded/api/v1/comments/add',{
        content: editor.getData(),
        code: drinkCode,
        type: 2,
        stars: $("#rating").val() || 0
    }).catch(e=>e);

    if(response instanceof Error)
        notify('Có lỗi xảy ra', 'danger');
    else
        notify('Đã thêm bình luận', 'success');

    editor.setData('');
    $("#rating").val(0)
    $(this).attr('disabled', false);
})

function renderLobbiesList(data) {
    let html = `<div class="mb-4 ">`
    $.each(data, function (index, item) {
        html += `
    <div  style="border-radius: 8px; border: 1px solid gray; margin-bottom: 10px; padding: 10px">
        <div class="text-muted fs-6 mt-3">Người vô danh</div>
        <div >${item.content}</div>
        <div class="d-flex justify-content-between fs-6 fw-lighter fst-italic">
        <div class="fs-4" style="margin-top: -15px !important;">${item.stars}/5 stars</div>
            <div>${moment(item.createdAt).format('HH:mm DD/MM/YYYY')}</div>
        </div>
    </div>
`

        let str = "#rating-"+item.id;
        console.log(str)
        $(str).val(2);
    });

    html+=`</div>`

    return html;
}

$(document).ready(async function () {
    const response = await axios.get(`/restaurant_war_exploded/api/v1/drinks/${drinkCode}`).catch(e=>e);
    if(response instanceof Error)
            return notify('Có lỗi xảy ra. Vui lòng thử lại !', 'erroe')

    drinkDetail = response.data.result;
    $('.drink-name').html(drinkDetail.name);
    $('#retail-price').html(dottedMoney(drinkDetail.price));
    $('#unit').html(dottedMoney(drinkDetail.unit));

    const container = $('#drink-detail-comments');
    const setting = {
        showPrevious: true,
        showNext: true,
        pageSize: 5,
        pageNumber: 8,
        dataSource: `/restaurant_war_exploded/api/v1/comments?code=${drinkCode}&type=2`,
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
                container.prev().html('Đang tải dữ liệu');
            }
        },
        callback: function (response, pagination) {
            container.prev().html(renderLobbiesList(response));
        }
    }
    container.pagination(setting)

});