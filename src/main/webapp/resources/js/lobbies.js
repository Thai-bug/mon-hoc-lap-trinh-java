
$(document).ready(function(){
    $('#lobbies').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Tên sảnh ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/admin/lobby/get_all',
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
                data: (lobby) => {
                    return lobby.id;
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (lobby) => {
                    return lobby.name;
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (lobby) => {
                    return dottedMoney(lobby.seats);
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (lobby) => {
                    return dottedMoney(lobby.money);
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
                    return moment(lobby.createdAt,'x').format('HH:mm DD/MM/YYYY');
                }
            },
            {
                class: 'text-center',
                data: (lobby) => {
                    return '<a data-id="' + lobby.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/lobby/' + lobby.code +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})