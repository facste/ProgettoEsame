$(function() {
	$("#menu").stick_in_parent()
});
$(document).ready(function() {
    $('form').submit(function () {
        var user = $.trim($('#user').val());
        var psw = $.trim($('#psw').val());
        if (user === '' || user.contains(";")) {
            alert('Username vuoto o contenente caratteri illegatli [; ,]');
            return false;
        }
        if (psw === '' || psw.contains(";")) {
            alert('Password vuoto o contenente caratteri illegatli [; ,]');
            return false;
        }
        if (psw.length<=7) {
            alert('Password troppo corta');
            return false;
        }
    });
});
