
$(document).ready(function(){
    $('#comments').DataTable({
        bLengthChange: true,
        ordering: true,
        processing: true,
        serverSide: true,
        destroy: true,
        scrollCollapse: true,
        language: {
            searchPlaceholder: "Mã số, nội dung ...",
            search: ''
        },
        ajax: {
            url:  window.location.origin + '/restaurant_war_exploded/api/v1/comments/admin/get_all',
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
                data: (comment) => {
                    return comment?.code;
                }
            },
            {
                class: 'text-center text-capitalize',
                data: (comment) => {
                    if(comment.lobby)
                        return 'Bình luận sảnh';
                    if(comment.service)
                        return 'Bình luận dịch vụ';
                    if(comment.food)
                        return 'Bình luận thức ăn';
                    if(comment.drink)
                        return 'Bình luận đồ uống';
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (comment) => {
                    return `${comment.stars}/5`;
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (comment) => {
                    return comment.status ? 'Đã duyệt' : 'Chưa duyệt';
                }
            },

            {
                class: 'text-center text-capitalize',
                data: (comment) => {
                    return moment(comment.createdAt).format('HH:MM DD/MM/YYYY');
                }
            },
            {
                class: 'text-center',
                data: (comment) => {
                    return '<a data-id="' + comment?.id + '" class="btn btn-outline-info mr-1 mb-3 edit" href="/restaurant_war_exploded/admin/comments/detail/' + comment?.code +'"><i class="fa fas fa-edit"></i></a>';
                }
            }
        ]
    });
})