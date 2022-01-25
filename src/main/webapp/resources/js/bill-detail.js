let data;
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

    $('#lobby').val(orderInfo.lobby?.name);

    $('#type').val(orderInfo.type?.title);

    $('#beginDate').val(moment(orderInfo.beginDate).format('DD/MM/YYYY HH:mm'));

    $('#bill').val(orderInfo.status?.title);

    $('#deposit').val(orderInfo.provisionalMoney?.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));

    $('#total').val(orderInfo.finalMoney?.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));

    localStorage.setItem('orderedFood', JSON.stringify(orderInfo.foodList));

    localStorage.setItem('orderedDrink', JSON.stringify(orderInfo.drinkList));

    localStorage.setItem('orderedService', JSON.stringify(orderInfo.serviceList));

    showFoods()

    showDrinks();

    showService();
}

const showFoods = () => {
    $("#food").empty();
    JSON.parse(localStorage.getItem('orderedFood'))
        .map((item, index) => {
            if (index % 2 === 0)
                return $("#food").append(
                    `<div class="ml-5 mt-2 badge bg-primary" id="food-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteFood(${item.id})" disabled>
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                    </div>`
                )

            return $("#food").append(
                `<div class="ml-5 mt-2 badge bg-warning" id="food-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" onclick="deleteFood(${item.id})" disabled>
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                 </div>`
            )
        })
}

const showDrinks = () => {
    JSON.parse(localStorage.getItem('orderedDrink'))
        .map((item, index) => {
        if (index % 2 === 0)
            return $("#drink").append(
                `<div class="ml-5 mt-2 badge bg-primary" id="drink-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" disabled>
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                    </div>`
            )

        return $("#drink").append(
            `<div class="ml-5 mt-2 badge bg-warning" id="drink-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" disabled>
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                 </div>`
        )
    })
}

const showService = (services) => {
    JSON.parse(localStorage.getItem('orderedService'))
        .map((item, index) => {
        if (index % 2 === 0)
            return $("#service").append(
                `<div class="ml-5 mt-2 badge bg-primary" id="service-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" disabled>
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                    </div>`
            )

        return $("#service").append(
            `<div class="ml-5 mt-2 badge bg-warning" id="service-${item?.id}"}>
                        <button type="button" class="btn-close" aria-label="Close" disabled>
                            <span aria-hidden="true">&times;</span>
                        </button>
                        ${item?.name}
                 </div>`
        )
    })
}

$(document).ready(function () {
    callApi(id);
})
