$(function () {
    $('#userType').change(function () {
        if ($('#userType').val() == 'студент') {
            $("#studendData").show();
            $("#group").attr('disabled',false);
        } else {
        	 $("#studendData").hide();
        	 $("#group").attr("disabled", true);
        }

    });
    
});
