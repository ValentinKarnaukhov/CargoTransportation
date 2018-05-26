$(document).ready(function(){
    $('.table').DataTable( {
        "pagingType": "simple_numbers"
    } );

    $('select').material_select();

    $("#changeCity").on('click',function () {
       $('#city_ok').show();
       $('#city_decline').show();
    });

    $('#city_ok').on('click',function () {
       $.ajax({
           type: 'get',
           url: '/driver/'+$('#driver_id').val()+'/changeCity',
           data: ({
               city_id: $('#changeCity option:selected').val()
           })
       }) ;
        $('#city_ok').hide();
        $('#city_decline').hide();
    });
    $('#city_decline').on('click',function () {
        $('#city_ok').hide();
        $('#city_decline').hide();
    });

   $("#changeStatus").on('click',function () {
       $('#ok').show();
       $('#decline').show();
    });

    $('#ok').on('click',function () {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: '/driver/'+$('#driver_id').val()+'/change',
            data: ({
                status: $('#changeStatus option:selected').text()
            })
        });
        $('#ok').hide();
        $('#decline').hide();
    });
    $('#decline').on('click',function () {
        $('#ok').hide();
        $('#decline').hide();
    });

});

$('[name = finish]').on('submit',function () {
    var choose = $('.active').length-1;
    var max = $('.maxDrivers').val();
    var errors = $('.errors');
    errors.empty();
    if(choose!=max){
        errors.append("You should choose ", max, " drivers");
        return false;
    }
});

if($('.created').length==1)Materialize.toast("Order was created", 2000);


$(document).on('click','.row_data',function () {
    var tbl_row = $(this).closest('tr');
    tbl_row.find('.btn_ok').show();
    tbl_row.find('.btn_decline').show();
});

$(document).on('click','.btn_ok',function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var tbl_row = $(this).closest('tr');
    var row_id = tbl_row.attr('row_id');

    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        type: 'post',
        url: '/driver/cargo_change',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            point_id: row_id,
            status: tbl_row.find('.cargoStatus option:selected').text()
        })
    }).done(function (result) {
        if(result.point_id===0){
            tbl_row.empty();
            Materialize.toast("Order was completed", 2000);
            return false;
        }
        if(result.status==='DONE')tbl_row.empty();
        tbl_row.attr('row_id',result.point_id);
        tbl_row.find('.city').text(result.city);
        tbl_row.find('.operation').text('UNLOADING');
        tbl_row.find('.cargoStatus option[value=1]').prop('selected',true)

    });
    tbl_row.find('.btn_ok').hide();
    tbl_row.find('.btn_decline').hide();

});

$(document).on('click', '.btn_decline',function () {
    var tbl_row = $(this).closest('tr');
    tbl_row.find('.btn_ok').hide();
    tbl_row.find('.btn_decline').hide();
    tbl_row.find('.cargoStatus option[value=1]').prop('selected',true)
});












