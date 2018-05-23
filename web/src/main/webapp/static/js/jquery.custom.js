$(document).ready(function(){
    $('select').material_select();


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

if($('.created').length==1)Materialize.toast("Order was created", 2000)


$(document).on('click','.row_data',function () {
    var tbl_row = $(this).closest('tr');
    // var row_id = tbl_row.attr('row_id');
    // alert(tbl_row.find('.cargoStatus option:selected').text());
    tbl_row.find('.btn_ok').show();
    tbl_row.find('.btn_decline').show();
});

$(document).on('click','.btn_ok',function () {

    var tbl_row = $(this).closest('tr');
    var row_id = tbl_row.attr('row_id');
    $.ajax({
        type: 'get',
        contentType: 'application/json',
        url: '/driver/'+row_id+'/cargo_change',
        data: ({
            status: tbl_row.find('.cargoStatus option:selected').text()
        })
    });
    tbl_row.find('.btn_ok').hide();
    tbl_row.find('.btn_decline').hide();

});
$(document).on('click', '.btn_decline',function () {
    var tbl_row = $(this).closest('tr');
    tbl_row.find('.btn_ok').hide();
    tbl_row.find('.btn_decline').hide();
});












