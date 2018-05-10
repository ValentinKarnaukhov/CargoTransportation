$(document).ready(function(){
    $('select').material_select();
});

$(document).ready(function () {

    $('#test').click(function () {
        $.ajax({
            url: "/login",
            data: "TEST"
        })
    });
    
});