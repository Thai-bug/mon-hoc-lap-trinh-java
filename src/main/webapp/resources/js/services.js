
$(document).ready(function(){
    $('#service').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Tên dịch vụ ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/admin/services/get_all',
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
                data: (service) => {
                    return service.id;
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (service) => {
                    return service.name;
                }
            },
            

            {
                class: 'text-center text-capitalize',
                data: (service) => {
                    return dottedMoney(service.price);
                }
            },
            {
                class: 'text-center',
                data: (service) => {
                    return service.status === true ?  'Hoạt động' :'Tạm dừng';
                }
            },
            {
                class: 'text-center',
                data: (service) => {
                    return moment(service.createdAt).format('HH:mm DD/MM/YYYY');
                }
            },
            {
                class: 'text-center',
                data: (service) => {
                    return '<a data-id="' + service.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/services/detail/' + service.code +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})