let data;

let orderedFood = [];

let orderedDrink = [];

let orderedService = [];

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

const passData = (orderInfo) => {
    $('#code').val(orderInfo.code);

    $('#customer-name').val(orderInfo.customerName);

    $('#name').val(orderInfo.name);

    const employeeName = orderInfo.employee.firstName + ' ' + orderInfo.employee.lastName
    $('#employee').val(employeeName);

    $('#lobby').val(orderInfo.lobby.name);

    $('#beginDate').val(moment(orderInfo.beginDate).format('DD/MM/YYYY HH:mm'));

    $('#bill').val(orderInfo.status.title);

    $('#deposit').val(orderInfo.provisionalMoney.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));

    $('#total').val(orderInfo.finalMoney.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));

    $('#tables').val(orderInfo.totalTable);

    orderedFood = orderedFood.concat(orderInfo.foodList);
    orderedDrink = orderedDrink.concat(orderInfo.drinkList);
    orderedService = orderedService.concat(orderInfo.serviceList);

    showFoods(orderedFood)

    showDrinks(orderedDrink);

    showService(orderedService);
}

const showFoods = (foods) => {
    foods.map((item, index) => {
        if (index % 2 === 0)
            return $("#food").append(
                `<div class="ml-5 mt-2 badge badge-accent badge-lg leading-loose" id="food-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteDrink(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
            )

        return $("#food").append(
            `<div class="ml-5 mt-2 badge badge-success badge-lg leading-loose" id="drink-${item.id}"}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-4 h-4 mr-2 stroke-current" onclick="deleteDrink(${item.id})">--%>
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                        ${item.name}
                    </div>`
        )
    })
}

const showDrinks = (drinks) => {
    drinks.map((item, index) => {
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

const showService = (services) => {
    const html = services.map((item, index) => {
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

const deleteService = (id) => {
    $("#service-" + id.toString()).remove()
}

const deleteDrink = (id) => {
    $("#drink-" + id.toString()).remove()
}

const deleteFood = (id) => {
    $("#food-" + id.toString()).remove()
}

$(document).ready(function () {
    callApi(id);
})