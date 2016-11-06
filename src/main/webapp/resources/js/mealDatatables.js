var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render":function (data,type,row) {
                    if (type='display')    {
                        return data.substring(0,10)+' '+data.substring(11,16);
                    }
                    return data;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },

            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ]
        ,
        "order": [
            [
                0,
                "asc"
            ]
        ]
      ,
        "createdRow": function (row, data, dataIndex) {
            if (!data.exceed) {
                $(row).addClass('normal');
                   // .css('color','green');
            }
            else {
                $(row).addClass('exceeded');
                //$(row).css('color','red');
            }
        },
        "initComplete": function () {
            makeEditable();
        }
    });
});

