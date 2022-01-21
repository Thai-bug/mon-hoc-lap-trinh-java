
$(document).ready(function(){
    $('#bills').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Tên bữa tiệc, tên khách hàng ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/admin/bills/get_all',
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
                data: (bill) => {
                    return bill?.id;
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (bill) => {
                    return `${bill?.name}`;
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (bill) => {
                    return dottedMoney(bill?.finalMoney);
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (bill) => {
                    return dottedMoney(bill?.provisionalMoney);
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (bill) => {
                    return bill?.employee?.firstName + ' ' + bill?.employee?.lastName;
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
                data: (lobby) => {
                    return '<a data-id="' + lobby?.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/bills/detail?id=' + lobby?.id +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})