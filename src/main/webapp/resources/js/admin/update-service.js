let serviceCode = window.location.pathname.split('/').at(-1);
let serviceDetail = {};
let editor;

ClassicEditor
    .create(document.querySelector('#description'), {
        placeholder: 'Cập nhật mô tả dịch vụ'
    })
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

const schemaUpdate = joi.object({
    code: joi.string().required().messages({
        "any.required": "Mã dịch vụ không hợp lệ!!",
    }),
    name: joi.string().required().messages({
        "any.required": "Vui lòng nhập tên dịch vụ!!",
    }),
    status: joi.boolean().required().messages({
        "object.unknown": "Trạng thái không hợp lệ!!",
    }),
    price: joi.number().min(0).required().messages({
        "any.required": "Vui lòng nhập đơn giá!!",
        "number.min": "Đơn giá không hợp lệ!!",
        "number.base": "Vui lòng nhập đơn giá chính xác"
    }),
    description: joi.string().allow(null, '')
})

$(document).ready(async function () {
    const response = await axios.get(`/restaurant_war_exploded/api/v1/admin/services/${serviceCode}`).catch(e=>e);
    if(response instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lai', 'error');

    serviceDetail = response.data.result;

    $('#service-code').val(serviceDetail.code);

    $('#service-name').val(serviceDetail.name);

    $('#service-status').val(serviceDetail.status ? 1 : 0);

    $('#retail-price').val(serviceDetail.price);

    editor.setData(serviceDetail.description);
})

$('#update-btn').on('click', async function () {
    const requestData = {
        code: $('#service-code').val(),
        name: $('#service-name').val(),
        status: +$('#service-status').val() === 1 ? true : false,
        price: $('#retail-price').val(),
        description: editor.getData()
    }

    const validate = await schemaUpdate.validateAsync(requestData).catch(e=>e);
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error');

    $('#update-btn').attr('disabled', true);
    const updateResponse = await axios.post('/restaurant_war_exploded/api/v1/admin/services/update',
        requestData).catch(e=>e);
    $('#update-btn').attr('disabled', false);

    if(updateResponse instanceof Error)
        return notifyToast('Có lỗi xảy ra. Vui lòng thử lại', 'error');

    return notifyToast('Cập nhật thành công', 'success');
})