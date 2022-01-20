
$(document).ready(function(){
    $('#food').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Tên thức ăn ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/admin/foods/get_all',
            type: 'POST',
            dataType: 'json',
            "contentType": "application/json; charset=utf-8",
            data: function (param) {
                return JSON.stringify( param )
                // param.store_type = $('#store_type_view').val();
            }
        },
        dataType: "json",
        aaSorting: [
            [0, 'desc']
        ],
        columns: [
            {
                class: 'text-center text-capitalize',
                data: (food) => {
                    return food?.id;
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (food) => {
                    return `${food?.name}`;
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (food) => {
                    return dottedMoney(food?.price);
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (food) => {
                    return food.unit;
                }
            },
            {
                class: 'text-center',
                data: (lobby) => {
                    return lobby.status === true ?  'Hoạt động' :'Tạm dừng';
                }
            },
            {
                class: 'text-center',
                data: (lobby) => {
                    return moment(lobby?.created_at).format('HH:mm DD/MM/YYYY');
                }
            },
            {
                class: 'text-center',
                data: (food) => {
                    return '<a data-id="' + food?.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/food/detail/' + food?.id +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})