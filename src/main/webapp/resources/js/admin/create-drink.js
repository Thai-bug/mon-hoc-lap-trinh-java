let drinkDetail = {};
let editor;
let drinkCode = window.location.pathname.split('/').at(-1);

ClassicEditor
    .create(document.querySelector('#description'), {
        placeholder: 'Thêm mô tả đồ uống'
    })
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

const schemaUpdate = joi.object({
    name: joi.string().required().messages({
        "any.required": "Vui lòng nhập tên đồ uống!!",
    }),
    status: joi.boolean().required().messages({
        "object.unknown": "Trạng thái không hợp lệ!!",
    }),
    price: joi.number().min(0).required().messages({
        "any.required": "Vui lòng nhập đơn giá!!",
        "number.min": "Đơn giá không hợp lệ!!",
        "number.base": "Vui lòng nhập đơn giá chính xác"
    }),
    unit: joi.string().required().messages({
        "any.required": "Vui lòng nhập đơn vị tính!!",
    }),
    description: joi.string().allow(null, '')
})

$(document).ready(async function(){
})

$('#update-btn').on('click', async function () {
    const requestData ={
        name: $('#drink-name').val(),
        status: +$('#drink-status').val() === 1 ? true : false,
        price: $('#retail-price').val(),
        unit:  $('#unit').val(),
        description: editor.getData()
    }

    const validate = await schemaUpdate.validateAsync(requestData).catch(e=>e);
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error');
    //
    $('#update-btn').attr('disabled', true);
    const updateResponse = await axios.post('/restaurant_war_exploded/api/v1/admin/drinks/create',
        requestData).catch(e=>e);
    $('#update-btn').attr('disabled', false);
    //
    if(updateResponse instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lại', 'error');

    return notifyToast('Thêm đồ uống thành công', 'success');
})