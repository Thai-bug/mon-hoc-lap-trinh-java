
$(document).ready(function(){
    $('#drinks').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Tên đồ uống ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/admin/drinks/get_all',
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
                data: (drink) => {
                    return drink?.id;
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (drink) => {
                    return `${drink?.name}`;
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (drink) => {
                    return dottedMoney(drink?.price);
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (drink) => {
                    return drink.unit;
                }
            },
            {
                class: 'text-center',
                data: (drink) => {
                    return drink.status === true ?  'Hoạt động' :'Tạm dừng';
                }
            },
            {
                class: 'text-center',
                data: (drink) => {
                    return moment(drink?.createdAt).format('HH:mm DD/MM/YYYY');
                }
            },
            {
                class: 'text-center',
                data: (drink) => {
                    return '<a data-id="' + drink?.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/drinks/detail/' + drink?.code +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})