let lobbyDetail = {};
let editor;
let lobbyCode = window.location.pathname.split('/').at(-1);

const schemaUpdate = joi.object({
    name: joi.string().required().messages({
        "any.required": "Vui lòng nhập tên sảnh!!",
    }),
    age: joi.string().required().messages({
        "any.required": "Vui lòng nhập age!!",
    }),
})

$(document).ready(async function(){
    const lobbyDetail = await axios.get(`/restaurant_war_exploded/api/v1/admin/lobbies/${lobbyCode}`);
})

$('#update-btn').on('click', async function(){
    const validate = await schemaUpdate.validateAsync({ name: 'adb' }).catch(e=>e);
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error');
})