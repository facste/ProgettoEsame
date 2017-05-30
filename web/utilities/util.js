$(function() {
	$("#menu").stick_in_parent()
});
$(document).ready(function() {
    $('#log').submit(function () {
        var user = $.trim($('#user').val());
        var psw = $.trim($('#psw').val());
        if (user === ''  || user.search(';') != -1) {
            alert('Username vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (psw === ''  || psw.search(';') != -1) {
            alert('Password vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (psw.length<=7) {
            alert('Password troppo corta');
            return false;
        }
    });
});
$(document).ready(function() {
    $('#creaf').submit(function () {
        var namef = $.trim($('#nf').val());
        var namet = $.trim($('#namet').val());
        var surnamet = $.trim($('#surnamet').val());
        var telefono = $.trim($('#tel').val());
        var indirizzo = $.trim($('#if').val());
        if (namef === ''  || namef.search(';') != -1) {
            alert('Nome farmacia vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (namet === ''  || namet.search(';') != -1) {
            alert('Nome titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (surnamet === ''  || surnamet.search(';') != -1) {
            alert('Cognome titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (indirizzo === ''  || indirizzo.search(';') != -1) {
            alert('Indirizzo farmacia vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (telefono === ''  || !$.isNumeric(telefono)) {
            alert('Numero telefono non valido (può contenere solo numeri)');
            return false;
        }
    });
});
