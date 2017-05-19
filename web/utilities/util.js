$(function() {
	$("#menu").stick_in_parent()
});
$(document).ready(function() {
    $('form').submit(function () {
        var user = $.trim($('#user').val());
        var psw = $.trim($('#psw').val());
        if (user === '' || psw === '') {
            alert('Username or password are empty.');
            return false;
        }
    });
});
