$(document).ready(function(){
    $('select').material_select();
    $('#selectDriver').click(function () {
        $.ajax({
            url: 'test',
            type: 'get',
            success: function (data) {
                $('#test-swipe-3').append(
                    data
                );
            }
        });

    });

});


















$( document ).ready(function() {
    $(".inline-editable").hover(
        function() {$(this).addClass("editHover"); },
        function() { $(this).removeClass("editHover"); }
    );

    var oldText,
        newText;

    $(".inline-editable").bind("click", replaceHTML)

    function replaceHTML() {
        if(! $(".editing").length){
            oldText = $(this).html()
            $(this).addClass("editing");
            var form = createForm(oldText);
            $(this).html("")
                .html(form);
            $(this).unbind('click', replaceHTML);
        }
    }

    function createForm(text) {
        var form = "<form class=\"inline-editor\">";
        form += "<input type=\"text\" class=\"editBox\" value=\"";
        form += oldText;
        form += "\" /> </form>";
        form += "<a class=\"btnSave white-text btn \" style='margin-right: 10px'><i class=\"material-icons \">save</i></a></a>";
        form += "<a class=\"btnDiscard white-text btn\"><i class=\"material-icons \">cancel</i></a></a>";
        return form
    }

    $(document.body).on("click", ".btnDiscard", function() {
        $(this).parent().html(oldText);
        $(".inline-editable").removeClass("editing");
        $(".inline-editable").bind('click', replaceHTML);
        $(".inline-editable").removeClass("editHover");
    });

    $(document.body).on("click", ".btnSave", function() {
        newText = $(this).parent()
            .find(".editBox")
            .val();
        saveChanges(this, newText)
    });

    // use enter button
    $(document.body).on("submit", ".inline-editor", function(e) {
        e.preventDefault();
        var newText = $(this).find(".editBox").val()
        saveChanges(this, newText)
    })

    function saveChanges(x, newText) {
        var id =  $(x).closest("tr").attr("id");
        $.ajax({
            url: '/test',
            type: 'get',
            dataType: 'json',
            data: ({
                id: id,
                text: newText
            })

        });
        $(".inline-editable").removeClass("editing");
        $(this).parent().html(newText);
        $(".inline-editable").bind('click', replaceHTML);
        $(".inline-editable").removeClass("editHover");

    }
});