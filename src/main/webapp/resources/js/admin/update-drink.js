let drinkCode = window.location.pathname.split('/').at(-1);
let drinkDetail = {};
let editor;

ClassicEditor
    .create(document.querySelector('#description'), {
        placeholder: 'Cập nhật mô tả sảnh'
    })
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

const schemaUpdate = joi.object({
    code: joi.string().required().messages({
        "any.required": "Mã sảnh không hợp lệ!!",
    }),
    name: joi.string().required().messages({
        "any.required": "Vui lòng nhập tên sảnh!!",
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
    description: joi.string().allow(null, ''    )
})

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

    editor.setData(drinkDetail.description);
})

$('#update-btn').on('click', async function () {
    const requestData = {
        code: $('#drink-code').val(),
        name: $('#drink-name').val(),
        status: +$('#drink-status').val() === 1 ? true : false,
        price: $('#retail-price').val(),
        unit:  $('#unit').val(),
        description: editor.getData()
    }

    const validate = await schemaUpdate.validateAsync(requestData).catch(e=>e);
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error');

    $('#update-btn').attr('disabled', true);
    const updateResponse = await axios.post('/restaurant_war_exploded/api/v1/admin/drinks/update',
        requestData).catch(e=>e);
    $('#update-btn').attr('disabled', false);

    if(updateResponse instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lại', 'error');

    return notifyToast('Cập nhật thành công', 'success');
})