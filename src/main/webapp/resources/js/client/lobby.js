let lobbyDetail = {};
let editor;
let lobbyCode = window.location.pathname.split('/').at(-1);

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
    $(this).attr('disabled', true);
    const response = await axios.post('/restaurant_war_exploded/api/v1/comments/add',{
        content: editor.getData(),
        code: lobbyCode,
        type: 1
    });
    editor.setData('');
    $(this).attr('disabled', false);
})

function renderLobbiesList(data) {
    let html = `<ul>`
    $.each(data, function (index, item) {
        html += `
    <li>${item.content}</li>
`
    });

    html+=`</ul>`

    return html;
}

$(document).ready(async function () {
    const response = await axios.get(`/restaurant_war_exploded/api/v1/lobbies/${lobbyCode}`).catch(e=>e);
    if(response instanceof Error)
        return console.log('loi');

    lobbyDetail = response.data.result;
    $('.lobby-name').html(lobbyDetail.name);
    $('#retail-price').html(dottedMoney(lobbyDetail.money));
    $('#max-tables').html(dottedMoney(lobbyDetail.seats / 10));

    const container = $('#lobby-detail-comments');

    const setting = {
        showPrevious: true,
        showNext: true,
        pageSize: 8,
        pageNumber: 8,
        dataSource: `/restaurant_war_exploded/api/v1/comments?code=${lobbyCode}&type=1`,
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
            console.log(response)
            container.prev().html(renderLobbiesList(response));
        }
    }
    container.pagination(setting)

});