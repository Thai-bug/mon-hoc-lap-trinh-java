let data;

let orderedFood = [];

let orderedDrink = [];

let orderedService = [];

$(document).ready(function () {
    // $('.js-example-basic-single').select2();
    // $('#food-select').select2();
})

const id = new URLSearchParams(location.search).get("id")
const callApi = async (identity) => {
    const id = identity
    const urlString = `/restaurant_war_exploded/api/v1/admin/bills/detail/` + id
    const response = await fetch(urlString);
    data = await response.json();
    if (response.status === 200) {
        passData(data);
        $('#main').removeClass('hidden')
    }
}


const updateAction = async () => {
    // $( document ).ready(function() {
    //     Notify("Can't Touch This");
    //     Notify("Stop! Hammer time", null, null, 'danger');
    //
    //     Notify("I told you homeboy (You can't touch this)",
    //         function () {
    //             alert("clicked notification")
    //         },
    //         function () {
    //             alert("clicked x")
    //         },
    //         'success'
    //     );
    // });

    const data = {
        code: $('#code').val(),
        customerName: $('#customer-name').val(),
        name: $('#name').val(),
        employee: +$('#employee').attr('employee-id'),
        lobby: +$('#lobby').attr('lobby-id'),
        addedFood: JSON.parse(localStorage.getItem('addedFood')).map(item=>{return {id: item.id}}),
        deletedFood: JSON.parse(localStorage.getItem('deletedFood')).map(item=>{return {id: item.id}}),

        addedDrink: JSON.parse(localStorage.getItem('addedDrink')).map(item=>{return {id: item.id}}),
        deletedDrink: JSON.parse(localStorage.getItem('deletedDrink')).map(item=>{return {id: item.id}}),

        addedService: JSON.parse(localStorage.getItem('addedService')).map(item=>{return {id: item.id}}),
        deletedService: JSON.parse(localStorage.getItem('deletedService')).map(item=>{return {id: item.id}})
    }

    console.log(data);

    const response = await fetch('/restaurant_war_exploded/api/v1/admin/bills/update', {
        method: 'post',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(json=>json());

    console.log(response);
}

const passData = (orderInfo) => {
    data = orderInfo;

    $('#code').val(orderInfo.code);

    $('#customer-name').val(orderInfo.customerName);

    $('#name').val(orderInfo.name);

    const employeeName = orderInfo.employee.firstName + ' ' + orderInfo.employee.lastName
    $('#employee').val(employeeName);
    $('#employee').attr('employee-id', orderInfo.employee.id)

    $('#lobby').val(orderInfo.lobby.name);
    $('#employee').attr('lobby-id', orderInfo.lobby.id)

    $('#beginDate').val(moment(orderInfo.beginDate).format('DD/MM/YYYY HH:mm'));
    localStorage.setItem('beginDate', orderInfo.beginDate);

    $('#endDate').val(moment(orderInfo.endDate).format('DD/MM/YYYY HH:mm'));
    localStorage.setItem('endDate', orderInfo.endDate);

    $('#bill').val(orderInfo.status?.title);

    $('#deposit').val(dottedMoney(orderInfo.provisionalMoney));

    $('#total').val(dottedMoney(orderInfo.finalMoney));

    $('#tables').val(orderInfo.totalTable);
    localStorage.setItem('tables', orderInfo.totalTable);

    $('<option>').val(orderInfo?.lobby?.id)
        .text(orderInfo?.lobby?.name)
        .attr('selected', true)
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
                    `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="food-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteFood(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
                )

            return $("#food").append(
                `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="drink-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteFood(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
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
                    `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="drink-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteDrink(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
                )

            return $("#drink").append(
                `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="drink-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteDrink(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
            )
        })
}

const showService = () => {
    $("#service").empty();
    const html = JSON.parse(localStorage.getItem('orderedService'))
        .map((item, index) => {
            if (index % 2 === 0)
                return `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="service-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteService(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
            return `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="service-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteService(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
        }).join("");

    document.querySelector('#service').insertAdjacentHTML("afterbegin", html);
}

$(document).ready(function () {
    callApi(id);
})

//add food
$(document.body).on("change", "#food-list", function () {
    let addedFood = !localStorage.getItem('addedFood') ? [] : JSON.parse(localStorage.getItem('addedFood'));
    let deletedFood = !localStorage.getItem('deletedFood') ? [] : JSON.parse(localStorage.getItem('deletedFood'));
    let orderedFood = !localStorage.getItem('orderedFood') ? [] : JSON.parse(localStorage.getItem('orderedFood'));

    const text = $(this).find("option:selected").text();
    if (!orderedFood.filter(item => +item.id === +this.value)[0]) {
        orderedFood.push({
            id: +this.value,
            name: text,
            price: +$(this).select2('data')[0].price
        });

        const totalPrice = +$('#total').val().replace(/\./g, '')
            + +$('#tables').val() * $(this).select2('data')[0].price;
        $('#total').val(dottedMoney(totalPrice));
    }

    if (!addedFood.filter(item => +item === +this.value)[0]) {
        addedFood.push(+this.value);
    }

    deletedFood = deletedFood.filter(item => +item.id !== +this.value);


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

    if (
        !deletedFood.filter(item => +item === +id)[0] && data.foodList.filter(item => item.id === +id).length > 0) {
        deletedFood.push(+id);
    }

    addedFood = addedFood.filter(item => +item.id !== +id);
    orderedFood = orderedFood.filter(item => +item.id !== +id);

    localStorage.setItem('deletedFood', JSON.stringify(deletedFood));
    localStorage.setItem('addedFood', JSON.stringify(addedFood));
    localStorage.setItem('orderedFood', JSON.stringify(orderedFood));

    showFoods();
}

//add drink
$(document.body).on("change", "#drink-list", function () {
    let addedDrink = !localStorage.getItem('addedDrink') ? [] : JSON.parse(localStorage.getItem('addedDrink'));
    let deletedDrink = !localStorage.getItem('deletedDrink') ? [] : JSON.parse(localStorage.getItem('deletedDrink'));
    let orderedDrink = !localStorage.getItem('orderedDrink') ? [] : JSON.parse(localStorage.getItem('orderedDrink'));

    const text = $(this).find("option:selected").text();
    if (
        !orderedDrink.filter(item => +item.id === +this.value)[0]) {

        orderedDrink.push({
            id: +this.value,
            name: text,
            price: +$(this).select2('data')[0].price
        });
    }

    if (!addedDrink.filter(item => +item === +this.value)[0]) {
        addedDrink.push(+this.value);
    }

    deletedDrink = deletedDrink.filter(item => +item.id !== +this.value);

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

    if (
        !deletedDrink.filter(item => +item === +id)[0] && data.drinkList.filter(item => item.id === +id).length > 0) {
        deletedDrink.push(+id);
    }

    addedDrink = addedDrink.filter(item => +item.id !== +id);
    orderedDrink = orderedDrink.filter(item => +item.id !== +id);

    localStorage.setItem('deletedDrink', JSON.stringify(deletedDrink));
    localStorage.setItem('addedFood', JSON.stringify(addedDrink));
    localStorage.setItem('orderedDrink', JSON.stringify(orderedDrink));

    showDrinks();
}

//add service
$(document.body).on("change", "#service-list", function () {
    let addedService = !localStorage.getItem('addedService') ? [] : JSON.parse(localStorage.getItem('addedService'));
    let deletedService = !localStorage.getItem('deletedService') ? [] : JSON.parse(localStorage.getItem('deletedService'));
    let orderedService = !localStorage.getItem('orderedService') ? [] : JSON.parse(localStorage.getItem('orderedService'));

    const text = $(this).find("option:selected").text();
    if (
        !orderedService.filter(item => +item.id === +this.value)[0]) {

        orderedService.push({
            id: +this.value,
            name: text,
            price: +$(this).select2('data')[0].price
        });
    }

    if (!addedService.filter(item => +item === +this.value)[0]) {
        addedService.push(+this.value);
    }

    deletedService = deletedService.filter(item => +item.id !== +this.value);

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

    if (
        !deletedService.filter(item => +item === +id)[0] && data.deletedService.filter(item => item.id === +id).length > 0) {
        deletedService.push(+id);
    }

    addedService = addedService.filter(item => +item.id !== +id);
    orderedService = orderedService.filter(item => +item.id !== +id);

    localStorage.setItem('deletedService', JSON.stringify(deletedService));
    localStorage.setItem('addedService', JSON.stringify(addedService));
    localStorage.setItem('orderedService', JSON.stringify(orderedService));

    showDrinks();
}

$('#update-bill').on('click', function () {
    updateAction();
})
