let employee;
let total = 0;
let data;
let tables = 0;
let oldTables = 0;
let preOrder = 0;
const getEmployee = async () => {
    if(window.location.pathname.includes('/bills/create')) {
        const urlString = `/restaurant_war_exploded/api/v1/employees`
        const response = await fetch(urlString);
        employee = await response.json();
        if (response.status === 200) {
            $('#main').removeClass('hidden')
        }
    }
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
                return  `<div class="ml-5 mt-2 badge bg-primary" id="service-${item?.id}"}>
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

const deleteFood = (id) => {
    let addedFood = !localStorage.getItem('addedFood') ? [] : JSON.parse(localStorage.getItem('addedFood'));
    let orderedFood = !localStorage.getItem('orderedFood') ? [] : JSON.parse(localStorage.getItem('orderedFood'));
    total -= orderedFood.filter(item => +item?.id === +id)[0].price * tables;
    $('#total').val(dottedMoney(total));

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    addedFood = addedFood.filter(item => +item?.id !== +id);
    orderedFood = orderedFood.filter(item => +item?.id !== +id);

    localStorage.setItem('addedFood', JSON.stringify(addedFood));
    localStorage.setItem('orderedFood', JSON.stringify(orderedFood));

    showFoods();
}

const deleteDrink = (id) => {
    let addedDrink = !localStorage.getItem('addedDrink') ? [] : JSON.parse(localStorage.getItem('addedDrink'));
    let orderedDrink = !localStorage.getItem('orderedDrink') ? [] : JSON.parse(localStorage.getItem('orderedDrink'));
    total -= orderedDrink.filter(item => +item?.id === +id)[0].price * tables;
    $('#total').val(dottedMoney(total));

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    addedDrink = addedDrink.filter(item => +item?.id !== +id);
    orderedDrink = orderedDrink.filter(item => +item?.id !== +id);

    localStorage.setItem('addedDrink', JSON.stringify(addedDrink));
    localStorage.setItem('orderedDrink', JSON.stringify(orderedDrink));

    showDrinks();
}

const deleteService = (id) => {
    let addedService = !localStorage.getItem('addedService') ? [] : JSON.parse(localStorage.getItem('addedService'));
    let orderedService = !localStorage.getItem('orderedService') ? [] : JSON.parse(localStorage.getItem('orderedService'));
    total -= orderedService.filter(item => +item?.id === +id)[0].price;
    $('#total').val(dottedMoney(total));

    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));

    addedService = addedService.filter(item => +item?.id !== +id);
    orderedService = orderedService.filter(item => +item?.id !== +id);

    localStorage.setItem('addedService', JSON.stringify(addedService));
    localStorage.setItem('orderedService', JSON.stringify(orderedService));

    showService();
}

$(async function () {
    await getEmployee();
    $('#employee').val(`${employee.firstName} ${employee.lastName}`);
    $('#total').val(total)
    $('#tables-bill').val(dottedMoney(tables));
})

$('#tables-bill').on('keyup', function () {
    if(isNaN($(this).val().replace(/\./g, '')))
        return;
    tables = +$(this).val().replace(/\./g, '');

    changeTablesAction('food',tables, oldTables);
    changeTablesAction('drink',tables, oldTables);
    oldTables = tables
    $('#tables-bill').val(dottedMoney(tables));
})

$('#pre-order').on('change', function(e){
    preOrder = $(this).is(':checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));
})

$(document.body).on("change", "#food-list", function () {
    if(!$("#food-list").val())
        return;
    let addedFood = !localStorage.getItem('addedFood') ? [] : JSON.parse(localStorage.getItem('addedFood'));
    let orderedFood = !localStorage.getItem('orderedFood') ? [] : JSON.parse(localStorage.getItem('orderedFood'));

    if (!addedFood.filter(item => +item?.id === +this.value)[0]) {
        orderedFood.push({
            id: +this.value,
            name: $(this).select2('data')[0].text,
            price: +$(this).select2('data')[0].price
        });

        addedFood.push({
            id: +this.value
        })

        total = +total
            + tables * $(this).select2('data')[0].price;
        preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
        $('#deposit').val(dottedMoney(preOrder));
        $('#total').val(dottedMoney(total));
    }
    localStorage.setItem('addedFood', JSON.stringify(addedFood));
    localStorage.setItem('orderedFood', JSON.stringify(orderedFood));

    showFoods();
    $('#food-list').val(null).trigger('change')
})

$(document.body).on("change", "#drink-list", function () {
    if(!$("#drink-list").val())
        return;
    let addedDrink = !localStorage.getItem('addedDrink') ? [] : JSON.parse(localStorage.getItem('addedDrink'));
    let orderedDrink = !localStorage.getItem('orderedDrink') ? [] : JSON.parse(localStorage.getItem('orderedDrink'));

    if (!addedDrink.filter(item => +item?.id === +this.value)[0]) {
        orderedDrink.push({
            id: +this.value,
            name: $(this).select2('data')[0].text,
            price: +$(this).select2('data')[0].price
        });

        addedDrink.push({
            id: +this.value
        })

        total = +total
            + tables * $(this).select2('data')[0].price;
        preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
        $('#deposit').val(dottedMoney(preOrder));
        $('#total').val(dottedMoney(total));
    }
    localStorage.setItem('addedDrink', JSON.stringify(addedDrink));
    localStorage.setItem('orderedDrink', JSON.stringify(orderedDrink));

    showDrinks();
    $('#drink-list').val(null).trigger('change')
})

$(document.body).on("change", "#service-list", function () {
    if(!$("#service-list").val())
        return;
    let addedService = !localStorage.getItem('addedService') ? [] : JSON.parse(localStorage.getItem('addedService'));
    let orderedService = !localStorage.getItem('orderedService') ? [] : JSON.parse(localStorage.getItem('orderedService'));

    if (!orderedService.filter(item => +item?.id === +this.value)[0]) {
        orderedService.push({
            id: +this.value,
            name: $(this).select2('data')[0].text,
            price: +$(this).select2('data')[0].price
        });

        addedService.push({
            id: +this.value
        })

        total = +total
            + $(this).select2('data')[0].price;
        preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
        $('#deposit').val(dottedMoney(preOrder));
        $('#total').val(dottedMoney(total));
    }
    localStorage.setItem('addedService', JSON.stringify(addedService));
    localStorage.setItem('orderedService', JSON.stringify(orderedService));

    showService();
    $('#service-list').val(null).trigger('change')
})

const validateCreateSchema = joi.object({
    tables: joi.number().required(),
    total: joi.number().required(),
    deposit: joi.number().required(),
    beginDate: joi.number().required(),
    endDate: joi.number().required(),
    name: joi.string().required(),
    customerName: joi.string().required(),
    status: joi.object({
        id: joi.number().required(),
    }).required(),
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
})

async function createOrder(){
    const begin = moment($('#beginDate').val(), 'DD/MM/YYYY hh:mm').valueOf();
    const end = moment($('#endDate').val(), 'DD/MM/YYYY hh:mm').valueOf();

    const requestData = {
        tables: tables,
        total: total,
        deposit: preOrder,
        addedFoods: JSON.parse(localStorage.getItem('orderedFood')) || [],
        addedDrinks: JSON.parse(localStorage.getItem('orderedDrink')) || [],
        addedServices: JSON.parse(localStorage.getItem('orderedService')) || [],
        beginDate: begin,
        endDate: end,
        lobby: {id: +$('#lobby-select').val()},
        employee: {id: employee.id},
        status: {id: $('#pre-order').prop('checked') ? 2 : 1},
        type: {id: $('#type').val()},
        customerName: $('#customer-name').val(),
        name: $('#name').val(),
    };

    const validate = await validateCreateSchema.validateAsync(requestData).catch(e=>e)
    if(validate instanceof Error)
        return notifyToast(validate.message, 'error')

    const response = await axios.post('/restaurant_war_exploded/api/v1/admin/bills/create', validate ).catch(e=>e);

    if (response instanceof Error) {
        return notifyToast(response?.response.data.message,'error'
        );
    }

    return notifyToast('Tạo đơn hàng thành công','success');
}