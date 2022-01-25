
$(document).ready(function(){
    $('#employees').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Tên nhân viên ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/employees/get_all',
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
                data: (employee) => {
                    return employee?.id || 'N/A';
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (employee) => {
                    return employee?.firstName ?  `${employee?.firstName} ${employee?.lastName}` : 'N/A';
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (employee) => {
                    return employee?.email || 'N/A';
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (employee) => {
                    return employee?.parent?.firstName ? employee?.parent?.firstName + ' ' + employee?.parent?.lastName : 'N/A'
                }
            },
            {
                class: 'text-center',
                data: (employee) => {
                    return employee.status === true ?  'Hoạt động' :'Tạm dừng';
                }
            },
            {
                class: 'text-center',
                data: (employee) => {
                    return moment(employee?.created_at).format('HH:mm DD/MM/YYYY');
                }
            },
            {
                class: 'text-center',
                data: (employee) => {
                    return '<a data-id="' + employee?.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/employee/' + employee?.id +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})