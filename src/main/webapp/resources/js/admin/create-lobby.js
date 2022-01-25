let lobbyDetail = {};
let editor;
let lobbyCode = window.location.pathname.split('/').at(-1);

ClassicEditor
    .create(document.querySelector('#description'), {
        placeholder: 'Thêm mô tả sảnh'
    })
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

const schemaUpdate = joi.object({
    code: joi.string().allow(null, ''),
    name: joi.string().required().messages({
        "any.required": "Vui lòng nhập tên sảnh!!",
    }),
    status: joi.boolean().required().messages({
        "object.unknown": "Trạng thái không hợp lệ!!",
    }),
    money: joi.number().min(0).required().messages({
        "any.required": "Vui lòng nhập giá thuê!!",
        "number.min": "Giá thuê không hợp lệ!!",
        "number.base": "Vui lòng nhập giá thuê chính xác"
    }),
    seats: joi.number().min(100).max(2000).required().messages({
        "any.required": "Vui lòng nhập số bàn tối đa!!",
        "number.min": "Số bàn không hợp lệ!!",
        "number.max": "Số bàn không hợp lệ!!",
        "number.base": "Vui lòng nhập số bàn chính xác"
    }),
    description: joi.string().allow(null, ''    )
})

$(document).ready(async function(){
})

$('#update-btn').on('click', async function () {
    const requestData = {
        code: $('#lobby-code').val(),
        name: $('#lobby-name').val(),
        status: +$('#lobby-status').val() === 1 ? true : false,
        money: $('#lobby-price').val(),
        seats:  $('#lobby-tables').val() * 10,
        description: editor.getData()
    }

    const validate = await schemaUpdate.validateAsync(requestData).catch(e=>e);
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error');
    //
    $('#update-btn').attr('disabled', true);
    const updateResponse = await axios.post('/restaurant_war_exploded/api/v1/admin/lobbies/create',
        requestData).catch(e=>e);
    $('#update-btn').attr('disabled', false);
    //
    if(updateResponse instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lại', 'error');

    return notifyToast('Thêm sảnh thành công', 'success');
})