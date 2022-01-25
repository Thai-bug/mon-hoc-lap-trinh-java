let foodCode = window.location.pathname.split('/').at(-1);
let foodDetail = {};
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
        "any.required": "Mã đồ uống không hợp lệ!!",
    }),
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

$(document).ready(async function () {
    const response = await axios.get(`/restaurant_war_exploded/api/v1/admin/foods/${foodCode}`).catch(e=>e);
    if(response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    foodDetail = response.data.result;

    $('#food-code').val(foodDetail.code);

    $('#food-name').val(foodDetail.name);

    $('#food-status').val(foodDetail.status ? 1 : 0);

    $('#retail-price').val(foodDetail.price);

    $('#unit').val(foodDetail.unit);

    editor.setData(foodDetail.description);
})

$('#update-btn').on('click', async function () {
    const requestData = {
        code: $('#food-code').val(),
        name: $('#food-name').val(),
        status: +$('#food-status').val() === 1 ? true : false,
        price: $('#retail-price').val(),
        unit:  $('#unit').val(),
        description: editor.getData()
    }

    const validate = await schemaUpdate.validateAsync(requestData).catch(e=>e);
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error');

    $('#update-btn').attr('disabled', true);
    const updateResponse = await axios.post('/restaurant_war_exploded/api/v1/admin/foods/update',
        requestData).catch(e=>e);
    $('#update-btn').attr('disabled', false);

    if(updateResponse instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lại', 'error');

    return notifyToast('Cập nhật thành công', 'success');
})