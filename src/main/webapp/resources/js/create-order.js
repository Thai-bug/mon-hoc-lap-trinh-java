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

function changeTablesAction(type, tables, oldTables){
    let delta = tables - oldTables;
    switch (type){
        case 'food':
            if(localStorage.getItem('orderedFood')){
                let orderedFood = JSON.parse(localStorage.getItem('orderedFood'));
                total += orderedFood.reduce((temp, item) => {
                    return temp + +(item.price);
                }, 0)  * delta;
            }
            break;
        case 'drink':
            if(localStorage.getItem('orderedDrink')){
                let orderedDrink = JSON.parse(localStorage.getItem('orderedDrink'));
                total += orderedDrink.reduce((temp, item) => {
                    return temp + +(item.price);
                }, 0)  * delta;
            }
            break;
    }
    preOrder = $('#pre-order').prop('checked') ? total * 10 / 100 : 0;
    $('#deposit').val(dottedMoney(preOrder));
    $('#total').val(dottedMoney(total));
}

const showFoods = () => {
    $("#food").empty();
    JSON.parse(localStorage.getItem('orderedFood'))
        .map((item, index) => {
            if (index % 2 === 0)
                return $("#food").append(
                    `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="food-${item?.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteFood(${item?.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item?.name}
                    </div>`
                )

            return $("#food").append(
                `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="drink-${item?.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteFood(${item?.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
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
                    `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="drink-${item?.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteDrink(${item?.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item?.name}
                    </div>`
                )

            return $("#drink").append(
                `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="drink-${item?.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteDrink(${item?.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
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
                return `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="service-${item?.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteService(${item?.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item?.name}
                    </div>`
            return `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="service-${item?.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteService(${item?.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
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

async function createOrder(){
    const begin = moment($('#beginDate').val(), 'DD/MM/YYYY hh:mm').valueOf();
    const end = moment($('#endDate').val(), 'DD/MM/YYYY hh:mm').valueOf();
    console.log({beginDate, endDate})
    const response = await axios.post('/restaurant_war_exploded/api/v1/bills/create', {
        tables: tables,
        total: total,
        deposit: +$('#deposit').val() || 0,
        addedFoods: JSON.parse(localStorage.getItem('orderedFood')) || [],
        addedDrinks: JSON.parse(localStorage.getItem('orderedDrink')) || [],
        addedServices: JSON.parse(localStorage.getItem('orderedService')) || [],
        beginDate: begin,
        endDate: end,
        lobby: {id: +$('#lobby-select').val()},
        employee: {id: employee.id},
        status: {id: $('#pre-order').prop('checked') ? 2 : 1},
        customerName: $('#customer-name').val(),
        name: $('#name').val(),
    })
    // console.log(response);
}