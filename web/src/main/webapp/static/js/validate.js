$(document).ready(function () {

    $.validator.addMethod("email", function(value, element) {
        return this.optional(element) || /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/.test(value);
    }, email_incorrect);

    $.validator.addMethod("truckNumber", function(value, element) {
        return this.optional(element) || /^(\D*){2}(\d){5}$/.test(value);
    }, number_incorrect);

    $.validator.addMethod("letters", function(value, element) {
        return this.optional(element) || /^\D*$/.test(value);
    }, contains_digit);


    $('#waypoint').validate({
        rules:{
            'cargo.name':{
                maxlength:20,
                minlength:1
            },
            'cargo.weight':{
                max:10000,
                min:0
            }
        },
        errorElement : 'span',
        errorPlacement: function(error, element) {
            var placement = $(element).data('error');
            if (placement) {
                $(placement).append(error)
            } else {
                error.insertAfter(element);
            }
        }
    });

    $('#driver').validate({
       rules:{
           'first_name':{
               maxlength:20,
               minlength:1,
               letters:true
           },
           'last_name':{
               maxlength:20,
               minlength:1,
               letters:true
           },
           'worked_time':{
               max:300,
               min:0
           }
       },
        errorElement : 'span',
        errorPlacement: function(error, element) {
            var placement = $(element).data('error');
            if (placement) {
                $(placement).append(error)
            } else {
                error.insertAfter(element);
            }
        }
    });

    $('#truck').validate({
        rules:{
            'reg_number':{
                truckNumber:true
            },
            'max_drivers':{
                max:4,
                min:1
            },
            'capacity':{
                max:100000,
                min:100
            }
        },
        errorElement : 'span',
        errorPlacement: function(error, element) {
            var placement = $(element).data('error');
            if (placement) {
                $(placement).append(error)
            } else {
                error.insertAfter(element);
            }
        }
    });

});