let commentCode = window.location.pathname.split('/').at(-1);
let comment = {};
let editor;

ClassicEditor
    .create(document.querySelector('#comment'), {
        placeholder: 'Nội dung bình luận'
    })
    .then(newEditor => {
        newEditor.isReadOnly = true
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

$(document).ready(async function () {
    const response = await axios.get(`/restaurant_war_exploded/api/v1/comments/admin/detail/${commentCode}`).catch(e => e);
    if (response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    comment = response.data.result;
    $('#code').val(comment.code);

    let type = '';
    if(comment.drink)
        type = 'Đồ uống'
    if(comment.service)
        type = 'Dịch vụ'
    if(comment.food)
        type = 'Thức ăn'
    if(comment.lobby)
        type = 'Sảnh'

    $('#type').val(type);

    $('#rating').val(comment.stars);

    $('#createdAt').val(moment(comment.createdAt).format('HH:mm DD/MM/YYYY'));

    editor.setData(comment.content);
})

$('#active').on('click', async function(){
    const response = await axios.put(`/restaurant_war_exploded/api/v1/comments/admin/active/${commentCode}`).catch(e => e);
    if(response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    return notifyToast('Cập nhật thành công', 'success');
})


$('#disabled').on('click', async function(){
    const response = await axios.put(`/restaurant_war_exploded/api/v1/comments/admin/disabled/${commentCode}`).catch(e => e);
    if(response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    return notifyToast('Cập nhật thành công', 'success');
})