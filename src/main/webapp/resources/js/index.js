/**
 * GLOBAL REGION
 * */

Notify = function(text, callback, close_callback, style) {

    var time = '2000';
    var $container = $('#notifications');
    var icon = '<i class="fa fa-info-circle "></i>';

    if (typeof style == 'undefined' ) style = 'warning'

    var html = $('<div class="alert alert-' + style + '  hide">' + icon +  " " + text + '</div>');

    $('<a>',{
        text: '×',
        class: 'button close',
        style: 'padding-left: 10px;',
        href: '#',
        click: function(e){
            e.preventDefault()
            close_callback && close_callback()
            remove_notice()
        }
    }).prependTo(html)

    $container.prepend(html)
    html.removeClass('hide').hide().fadeIn('slow')

    function remove_notice() {
        html.stop().fadeOut('slow').remove()
    }

    var timer =  setInterval(remove_notice, time);

    $(html).hover(function(){
        clearInterval(timer);
    }, function(){
        timer = setInterval(remove_notice, time);
    });

    html.on('click', function () {
        clearInterval(timer)
        callback && callback()
        remove_notice()
    });


}

//document is ready to use
$(document).ready(function () {
    preprocessToggle();
})

File.prototype.convertToBase64 = function (callback) {
    let reader = new FileReader();
    reader.onloadend = function (e) {
        callback(e.target.result, e.target.error);
    };
    reader.readAsDataURL(this);
};

//preprocess toggle
function preprocessToggle() {
    let toggle = $('.status-select')
    let value = toggle.find(':selected').val()
    if (value === 'true') {
        toggle.addClass('select-success');
        $('.toggle-active-status-title').removeClass('hidden');
        return
    }
    toggle.addClass('select-error ');
    $('.toggle-disabled-status-title').removeClass('hidden');
}

/**
 * END REGION
 * */

/**
 * update avatar region
 *
 * */

$('#change-avatar').on("click", function () {
    $('#avatar-user').addClass("hidden")
    $('#avatar-upload').removeClass("hidden")
})

$('#cancel-avatar').on("click", function () {
    $('#avatar-user').removeClass("hidden")
    $('#avatar-upload').addClass("hidden")
})

$('#avatar').on('change', function (e) {
    let selectedFile = this.files[0];
    selectedFile.convertToBase64(function (base64) {
        $('#show-avatar').attr("src", base64);
        $('#update-avatar').attr("disabled", false);
    })
})

/**
 * PROCESS SELECT STATUS
 * */
$('.status-select').on('change', function (e) {
    let toggle = $(this)
    let value = toggle.find(':selected').val()
    if (value === 'true') {
        toggle.attr('value', 'false')
        $('.toggle-active-status-title').removeClass('hidden');
        $('.toggle-disabled-status-title').addClass('hidden');

        toggle.addClass('select-success');
        toggle.removeClass('select-error');
        return
    }
    $('.toggle-active-status-title').addClass('hidden');
    $('.toggle-disabled-status-title').removeClass('hidden');
    toggle.addClass('select-error');
    toggle.removeClass('select-success');
})

/**
 * END REGION
 * */

/**
 * end region
 * */

/**
 * ALERT BUTTON
 * */

$('.alert-button').on('click', function () {
    $('.alert').remove();
})

/**
 * END REGION
 * */


/**
 * FORMAT MONEY
 */
function dottedMoney(moneyString) {
    return moneyString.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
}

/**
 * END REGION
 */

let timeOut = 0;

$(document).ready(function () {
    $('#food-list').select2({
        placeholder: "Chọn món ăn để thêm",
        ajax: {
            url: '/restaurant_war_exploded/api/v1/admin/foods/select2/food-by-name',
            dataType: 'json',
            delay: 100,
            data: function (params) {
                console.log(params);
                let query = {
                    name: params.term,
                    status: true
                }

                // Query parameters will be ?search=[term]&type=public
                return query;
            },
            processResults: function (data) {
                return {
                    results: data.map(item => {
                        return {id: item.id, text: item.name, price: item.price}
                    })
                };
            },
            cache: true
        }
    });

    $('#drink-list').select2({
        placeholder: "Chọn đồ uống để thêm",
        ajax: {
            url: '/restaurant_war_exploded/api/v1/admin/drinks/select2/drink-by-name',
            dataType: 'json',
            delay: 100,
            data: function (params) {
                console.log(params);
                let query = {
                    name: params.term,
                    status: true
                }

                // Query parameters will be ?search=[term]&type=public
                return query;
            },
            processResults: function (data) {
                return {
                    results: data.map(item => {
                        return {id: item.id, text: item.name}
                    })
                };
            },
            cache: true
        }
    });

    $('#service-list').select2({
        placeholder: "Chọn dịch vụ để thêm",
        ajax: {
            url: '/restaurant_war_exploded/api/v1/admin/services/select2/service-by-name',
            dataType: 'json',
            delay: 100,
            data: function (params) {
                console.log(params);
                let query = {
                    name: params.term,
                    status: true
                }

                // Query parameters will be ?search=[term]&type=public
                return query;
            },
            processResults: function (data) {
                return {
                    results: data.map(item => {
                        return {id: item.id, text: item.name}
                    })
                };
            },
            cache: true
        }
    });

    $('#lobby-select').select2({
        placeholder: "Chọn sảnh để đổi",
        ajax: {
            url: '/restaurant_war_exploded/api/v1/admin/lobby/select2/lobby-by-name',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                let query = {
                    name: params.term,
                    status: true,
                    endDate: !$('#endDate') || $('#endDate') === '' ? null : moment($('#endDate').val(), 'DD/MM/YYYY hh:mm').valueOf(),
                    beginDate: !$('#beginDate') || $('#beginDate') === '' ? null : moment($('#beginDate').val(), 'DD/MM/YYYY hh:mm').valueOf()
                }

                return query;
            },
            processResults: function (data) {
                return {
                    results: data.map(item => {
                        return {id: item.id, text: item.name}
                    })
                };
            },
            cache: true
        }
    });
});

$('#lobby-select').on('change', function(){
    $('#lobby').val($(this).text());
    console.log($(this).val());
})


$('#tables').on('input', function () {
    clearTimeout(timeOut);

    timeOut = setTimeout(function () {
        let oldMoney = +$('#total').val().replace(/\./g, '');

        let foodMoney = 0;
        JSON.parse(localStorage.getItem('orderedFood')).forEach(item => {
            foodMoney += +item.price * +localStorage.getItem('tables');
        })

        let drinkMoney = 0;
        JSON.parse(localStorage.getItem('orderedDrink')).forEach(item => {
            drinkMoney += +item.price * +localStorage.getItem('tables');
        })

        let serviceMoney = 0;
        JSON.parse(localStorage.getItem('orderedDrink')).forEach(item => {
            serviceMoney += +item.price;
        })

        let temp = +$('#total').val().replace(/\./g, '')  - (foodMoney + drinkMoney + serviceMoney);

        foodMoney = 0;
        JSON.parse(localStorage.getItem('orderedFood')).forEach(item => {
            foodMoney += +item.price * +$('#tables').val();
        })

        drinkMoney = 0;
        JSON.parse(localStorage.getItem('orderedDrink')).forEach(item => {
            drinkMoney += +item.price * +$('#tables').val();
        })

        serviceMoney = 0;
        JSON.parse(localStorage.getItem('orderedDrink')).forEach(item => {
            serviceMoney += +item.price;
        })


        console.log({
            drinkMoney, foodMoney, serviceMoney, temp
        })

        $('#total').val(dottedMoney(temp + foodMoney + drinkMoney + serviceMoney));
        localStorage.setItem('tables', $('#tables').val());

    }, 250);
});

//reload window
$(window).on('load', function () {
    //food
    localStorage.removeItem('deletedFood');
    localStorage.removeItem('addedFood');
    localStorage.removeItem('orderedFood');

    //drink
    localStorage.removeItem('deletedDrink');
    localStorage.removeItem('orderedDrink');
    localStorage.removeItem('addedDrink');

    //service
    localStorage.removeItem('deletedService');
    localStorage.removeItem('orderedService');
    localStorage.removeItem('addedService');

    //date
    localStorage.removeItem('beginDate');
    localStorage.removeItem('endDate');
});