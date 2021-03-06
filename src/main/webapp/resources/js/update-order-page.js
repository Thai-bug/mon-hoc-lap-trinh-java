let data;
let orderedFood = [];
let orderedDrink = [];
let orderedService = [];
let total = 0;
let tables = 0;
let preOrder = 0;
let oldTables = 0;

$(document).ready(function () {
})

const id = new URLSearchParams(location.search).get("id")
const callApi = async (identity) => {
    if (window.location.pathname.includes('/bills/update')) {
        const id = identity
        const urlString = `/restaurant_war_exploded/api/v1/admin/bills/detail/` + id
        const response = await fetch(urlString);
        data = await response.json();
        if (response.status === 200) {
            passData(data);
            $('#main').removeClass('hidden')
        }
    }
}

const validateCreateSchema = joi.object({
    code: joi.string().required(),
    tables: joi.number().required(),
    total: joi.number().required(),
    deposit: joi.number().required(),
    name: joi.string().required(),
    customerName: joi.string().required(),
    employee: joi.object({
        id: joi.number().required(),
    }).required(),
    lobby: joi.object({
        id: joi.number().required(),
    }).required(),
    type: joi.object({
        id: joi.number().required(),
    }).required(),
    addedFoods: joi.array().allow(),
    addedDrinks: joi.array().allow(),
    addedServices: joi.array().allow(),
    deletedFoods: joi.array().allow(),
    deletedDrinks: joi.array().allow(),
    deletedServices: joi.array().allow(),
})

const updateAction = async (request) => {
    const response = await axios.post('/restaurant_war_exploded/api/v1/admin/bills/update', {
        ...request
    }).catch(e => e)

    return response
}

const passData = (orderInfo) => {
    data = orderInfo;
    total = data.finalMoney

    $('#code').val(orderInfo.code);

    $('#customer-name').val(orderInfo.customerName);

    $('#name').val(orderInfo.name);

    const employeeName = orderInfo.employee?.firstName + ' ' + orderInfo.employee?.lastName
    $('#employee').val(employeeName);
    $('#employee').attr('employee-id', orderInfo.employee?.id)

    $('<option>').val(orderInfo?.type?.id)
        .text(orderInfo?.type?.title)
        .appendTo(`#type`);

    lobbyPrice = orderInfo.lobby?.money;
    total = orderInfo?.finalMoney;
    tables = orderInfo?.totalTable;
    oldTables = orderInfo?.totalTable;
    preOrder = orderInfo?.provisionalMoney;
    if (preOrder > 0)
        $('#pre-order').prop('checked', true)

    $('#lobby').val(orderInfo.lobby?.name);
    $('#employee').attr('lobby-id', orderInfo.lobby?.id)

    $('#beginDate').val(moment(orderInfo.beginDate).format('DD/MM/YYYY HH:mm'));
    localStorage.setItem('beginDate', orderInfo.beginDate);

    $('#endDate').val(moment(orderInfo.endDate).format('DD/MM/YYYY HH:mm'));
    localStorage.setItem('endDate', orderInfo.endDate);
    $('#status').attr('status-id', orderInfo?.status?.id).val(orderInfo.status?.title);

    $('#deposit').val(dottedMoney(preOrder));

    $('#total').val(dottedMoney(total));

    $('#tables-bill').val(tables);
    localStorage.setItem('tables', orderInfo.totalTable);

    $('<option>').val(orderInfo?.lobby?.id)
        .text(orderInfo?.lobby?.name)
        .attr('selected', true)
        .attr('money', orderInfo?.lobby?.money)
        .appendTo(`#lobby-select`);

    orderedFood = orderedFood.concat(orderInfo.foodList);
    orderedDrink = orderedDrink.concat(orderInfo.drinkList);
    orderedService = orderedService.concat(orderInfo.serviceList);

    localStorage.setItem('orderedFood', JSON.stringify(orderedFood));
    localStorage.setItem('addedFood', JSON.stringify([]));
    localStorage.setItem('deletedFood', JSON.stringify([]));
    showFoods()

    localStorage.setItem('orderedDrink', JSON.stringify(orderedDrink));
    localStorage.setItem('addedDrink', JSON.stringify([]));
    localStorage.setItem('deletedDrink', JSON.stringify([]));
    showDrinks();

    localStorage.setItem('orderedService', JSON.stringify(orderedService));
    localStorage.setItem('addedService', JSON.stringify([]));
    localStorage.setItem('deletedService', JSON.stringify([]));
    showService();
}

const showFoods = () => {
    $("#food").empty();
    JSON.parse(localStorage.getItem('orderedFood'))
        .map((item, index) => {
            if (index % 2 === 0)
                return $("#food").append(
                    `<div class="ml-5 mt-2 badge bg-primary" id="food-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteFood(${item.id})">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                    </div>`
                )

            return $("#food").append(
                `<div class="ml-5 mt-2 badge bg-warning" id="food-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteFood(${item.id})">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                 </div>`
            )
        })
}

const showDrinks = () => {
    $("#drink").empty();
    JSON.parse(localStorage.getItem('orderedDrink'))
        .map((item, index) => {
            if (index % 2 === 0)
                return $("#drink").append(
                    `<div class="ml-5 mt-2 badge bg-primary" id="drink-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteDrink(${item.id})">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                    </div>`
                )

            return $("#drink").append(
                `<div class="ml-5 mt-2 badge bg-warning" id="drink-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteFood(${item.id})">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                 </div>`
            )
        })
}

const showService = () => {
    $("#service").empty();
    const html = JSON.parse(localStorage.getItem('orderedService'))
        .map((item, index) => {
            if (index % 2 === 0)
                return `<div class="ml-5 mt-2 badge bg-primary" id="service-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteService(${item.id})">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                    </div>`
            return `<div class="ml-5 mt-2 badge bg-warning" id="service-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteService(${item.id})">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                 </div>`
        }).join("");

    document.querySelector('#service').insertAdjacentHTML("afterbegin", html);
}

$(document).ready(function () {
    callApi(id);
})

$('#tables-bill').on('keyup', function () {
    console.log('helo')
    if (isNaN($(this).val().replace(/\./g, '')))
        return;
    tables = +$(this).val().replace(/\./g, '');

    changeTablesAction('food', tables, oldTables);
    changeTablesAction('drink', tables, oldTables);
    oldTables = tables
    $('#tables-bill').val(dottedMoney(tables));
})

//add food
$(document.body).on("change", "#food-list", function () {
    if (window.location.pathname.includes('/bills/create'))
        return
    let addedFood = !localStorage.getItem('addedFood') ? [] : JSON.parse(localStorage.getItem('addedFood'));
    let deletedFood = !localStorage.getItem('deletedFood') ? [] : JSON.parse(localStorage.getItem('deletedFood'));
    let orderedFood = !localStorage.getItem('orderedFood') ? [] : JSON.parse(localStorage.getItem('orderedFood'));

    const text = $(this).find("option:selected").text();
    if (!orderedFood.filter(item => +item?.id === +this.value)[0]) {
        orderedFood.push({
            id: +this.value,
            name: text,
            price: +$(this).select2('data')[0].price
        });

        total = total + +tables * $(this).select2('data')[0].price;
        $('#total').val(dottedMoney(total));
    }

    if (!addedFood.filter(item => +item === +this.value)[0]) {
        addedFood.push({id: +this.value});
    }

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    deletedFood = deletedFood.filter(item => +item?.id !== +this.value);

    localStorage.setItem('deletedFood', JSON.stringify(deletedFood));
    localStorage.setItem('addedFood', JSON.stringify(addedFood));
    localStorage.setItem('orderedFood', JSON.stringify(orderedFood));

    showFoods();
})

//delete food
const deleteFood = (id) => {
    let addedFood = !localStorage.getItem('addedFood') ? [] : JSON.parse(localStorage.getItem('addedFood'));
    let deletedFood = !localStorage.getItem('deletedFood') ? [] : JSON.parse(localStorage.getItem('deletedFood'));
    let orderedFood = !localStorage.getItem('orderedFood') ? [] : JSON.parse(localStorage.getItem('orderedFood'));

    total -= orderedFood.filter(item => +item?.id === +id)[0].price * tables;

    if (!deletedFood.filter(item => +item === +id)[0] && data.foodList.filter(item => item?.id === +id).length > 0) {
        deletedFood.push({
            id: +id
        });
    }

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    addedFood = addedFood.filter(item => +item?.id !== +id);
    orderedFood = orderedFood.filter(item => +item?.id !== +id);

    localStorage.setItem('deletedFood', JSON.stringify(deletedFood));
    localStorage.setItem('addedFood', JSON.stringify(addedFood));
    localStorage.setItem('orderedFood', JSON.stringify(orderedFood));

    showFoods();

    $(`#total`).val(dottedMoney(total));
}

//add drink
$(document.body).on("change", "#drink-list", function () {
    let addedDrink = !localStorage.getItem('addedDrink') ? [] : JSON.parse(localStorage.getItem('addedDrink'));
    let deletedDrink = !localStorage.getItem('deletedDrink') ? [] : JSON.parse(localStorage.getItem('deletedDrink'));
    let orderedDrink = !localStorage.getItem('orderedDrink') ? [] : JSON.parse(localStorage.getItem('orderedDrink'));

    const text = $(this).find("option:selected").text();
    if (!orderedDrink.filter(item => +item?.id === +this.value)[0]) {

        orderedDrink.push({
            id: +this.value,
            name: text,
            price: +$(this).select2('data')[0].price
        });

        total = total + +tables * $(this).select2('data')[0].price;
        $('#total').val(dottedMoney(total));
    }

    if (!addedDrink.filter(item => +item === +this.value)[0]) {
        addedDrink.push({
            id: +this.value
        });
    }

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    deletedDrink = deletedDrink.filter(item => +item?.id !== +this.value);

    localStorage.setItem('deletedDrink', JSON.stringify(deletedDrink));
    localStorage.setItem('addedDrink', JSON.stringify(addedDrink));
    localStorage.setItem('orderedDrink', JSON.stringify(orderedDrink));

    showDrinks();
})

//delete drink
const deleteDrink = (id) => {
    let addedDrink = !localStorage.getItem('addedDrink') ? [] : JSON.parse(localStorage.getItem('addedDrink'));
    let deletedDrink = !localStorage.getItem('deletedDrink') ? [] : JSON.parse(localStorage.getItem('deletedDrink'));
    let orderedDrink = !localStorage.getItem('orderedDrink') ? [] : JSON.parse(localStorage.getItem('orderedDrink'));

    total -= orderedDrink.filter(item => +item?.id === +id)[0].price * tables;

    if (
        !deletedDrink.filter(item => +item === +id)[0] && data.drinkList.filter(item => item?.id === +id).length > 0) {
        deletedDrink.push({
            id: +this.value
        });
    }

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    addedDrink = addedDrink.filter(item => +item?.id !== +id);
    orderedDrink = orderedDrink.filter(item => +item?.id !== +id);

    localStorage.setItem('deletedDrink', JSON.stringify(deletedDrink));
    localStorage.setItem('addedFood', JSON.stringify(addedDrink));
    localStorage.setItem('orderedDrink', JSON.stringify(orderedDrink));

    showDrinks();

    $(`#total`).val(dottedMoney(total));
}

//add service
$(document.body).on("change", "#service-list", function () {
    let addedService = !localStorage.getItem('addedService') ? [] : JSON.parse(localStorage.getItem('addedService'));
    let deletedService = !localStorage.getItem('deletedService') ? [] : JSON.parse(localStorage.getItem('deletedService'));
    let orderedService = !localStorage.getItem('orderedService') ? [] : JSON.parse(localStorage.getItem('orderedService'));

    const text = $(this).find("option:selected").text();
    if (
        !orderedService.filter(item => +item?.id === +this.value)[0]) {

        orderedService.push({
            id: +this.value,
            name: text,
            price: +$(this).select2('data')[0].price
        });

        total = +total + +$(this).select2('data')[0].price;
        $('#total').val(dottedMoney(total));
    }

    if (!addedService.filter(item => +item === +this.value)[0]) {
        addedService.push({
            id: +this.value
        });
    }

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    deletedService = deletedService.filter(item => +item?.id !== +this.value);

    localStorage.setItem('deletedService', JSON.stringify(deletedService));
    localStorage.setItem('addedService', JSON.stringify(addedService));
    localStorage.setItem('orderedService', JSON.stringify(orderedService));

    showService();
})

//delete drink
const deleteService = (id) => {
    let addedService = !localStorage.getItem('addedService') ? [] : JSON.parse(localStorage.getItem('addedService'));
    let deletedService = !localStorage.getItem('deletedService') ? [] : JSON.parse(localStorage.getItem('deletedService'));
    let orderedService = !localStorage.getItem('orderedService') ? [] : JSON.parse(localStorage.getItem('orderedService'));

    total -= orderedService.filter(item => +item?.id === +id)[0].price;

    if (
        !deletedService.filter(item => +item === +id)[0] && data.serviceList.filter(item => item?.id === +id).length > 0) {
        deletedService.push({
            id: +this.value
        });
    }

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    addedService = addedService.filter(item => +item?.id !== +id);
    orderedService = orderedService.filter(item => +item?.id !== +id);

    localStorage.setItem('deletedService', JSON.stringify(deletedService));
    localStorage.setItem('addedService', JSON.stringify(addedService));
    localStorage.setItem('orderedService', JSON.stringify(orderedService));

    showService();

    $(`#total`).val(dottedMoney(total));
}

$('#update-bill').on('click', async function () {
    if (data.status?.code === 'CANCEL' || data.status?.code === 'DONE') {
        return notifyToast('Kh??ng th??? c???p nh???t ????n h??ng!', 'error');
    }
    const request = {
        code: $('#code').val(),
        customerName: $('#customer-name').val(),
        name: $('#name').val(),
        employee: {id: +$('#employee').attr('employee-id')},
        lobby: {id: +$('#lobby-select').val()},

        total: total,
        deposit: preOrder,
        tables: tables,

        type: {id: +$('#type').val()},

        addedFoods: JSON.parse(localStorage.getItem('addedFood')).map(item => {
            return {id: item?.id}
        }),
        deletedFoods: JSON.parse(localStorage.getItem('deletedFood')).map(item => {
            return {id: item?.id}
        }),

        addedDrinks: JSON.parse(localStorage.getItem('addedDrink')).map(item => {
            return {id: item?.id}
        }),
        deletedDrinks: JSON.parse(localStorage.getItem('deletedDrink')).map(item => {
            return {id: item?.id}
        }),

        addedServices: JSON.parse(localStorage.getItem('addedService')).map(item => {
            return {id: item?.id}
        }),
        deletedServices: JSON.parse(localStorage.getItem('deletedService')).map(item => {
            return {id: item?.id}
        })
    }

    const validate = await validateCreateSchema.validateAsync(request).catch(e => e);
    if (validate instanceof Error)
        return notifyToast(validate.message, 'error')

    $('#update-bill').attr('disabled', true);
    const response = await updateAction(validate);
    $('#update-bill').removeAttr('disabled');
    if (response instanceof Error)
        return notifyToast('C???p nh???t ????n h??ng th???t b???i!', 'error');

    notifyToast('C???p nh???t ????n h??ng th??nh c??ng!', 'success');
    return setTimeout(() => {
        window.location.reload()
    }, 1500)
})

$('#cancel-bill').on('click', async function () {
    if (data.status?.code === 'CANCEL' || data.status?.code === 'DONE') {
        return notifyToast('Kh??ng th??? hu??? ????n h??ng!', 'error');
    }
    $('#cancel-bill').attr('disabled', true);
    const response = await axios.put(`/restaurant_war_exploded/api/v1/admin/bills/cancel/${$('#code').val()}`).catch(e => e);
    $('#cancel-bill').attr('disabled', false);
    if (response instanceof Error)
        return notifyToast('Hu??? ????n h??ng th???t b???i!', 'error');
    notifyToast('Hu??? ????n h??ng th??nh c??ng!', 'success');
    return setTimeout(() => {
        window.location.reload()
    }, 1500)
})

$('#paid-bill').on('click', async function () {
    if (data.status?.code === 'CANCEL' || data.status?.code === 'DONE') {
        return notifyToast('Kh??ng th??? thanh to??n ????n h??ng!', 'error');
    }
    $('#paid-bill').attr('disabled', true);
    const response = await axios.put(`/restaurant_war_exploded/api/v1/admin/bills/paid/${$('#code').val()}`).catch(e => e);
    $('#paid-bill').attr('disabled', false);
    if (response instanceof Error)
        return notifyToast('Thanh to??n ????n h??ng th???t b???i!', 'error');
    notifyToast('Thanh to??n ????n h??ng th??nh c??ng!', 'success');
    return setTimeout(() => {
        window.location.reload()
    }, 1500)
})