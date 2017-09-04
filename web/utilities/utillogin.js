/**
 * Created by facst on 09/08/2017.
 */
$(function () {
    $("#menu").stick_in_parent()
});
$(document).ready(function () {
    //AGGIUTA ELEMENTO
    $(".add").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        txt.val(parseInt(txt.val()) + 1);

    });
});
$(document).ready(function () {
    //DIMINUIZIONE ELEMENTO
    $(".sub").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        if (parseInt(txt.val()) != 0)
            txt.val(parseInt(txt.val()) - 1);
    });
});
$(document).ready(function () {
    //Ordine da fare
    $(".ordinare").click(function () {
        var elemento = $(this).attr('id').replace(/[^0-9]/g, '');
        var txt = $("input[name=ordina" + elemento + "]");
        var $tr = $(this).parents("tr");
        var idprodotto = $tr.find("td").eq(0).html().replace(/[^0-9]/g, ''); // idprodotto
        if (parseInt(txt.val()) <= 0 || !$.isNumeric(txt.val())) {
            alert('Quantità minore o uguale a zero o non numerico');
            return;
        }
        else {
            $.post("magazzino.jsp", {prodotto: idprodotto, valore: txt.val()}, function () {
                alert('Ordine effettuato con successo');
                location.reload();
            });
        }
    });
});
$(document).ready(function () {
    //NUOVA FARMACIA
    $('#creaf').submit(function () {
        var namef = $.trim($('#nf').val());
        var namet = $.trim($('#namet').val());
        var pass = $.trim($('#pass').val());
        var telefono = $.trim($('#tel').val());
        var indirizzo = $.trim($('#if').val());
        var cognomet = $.trim($('#cognomet').val());
        var user = $.trim($('#user').val());
        if (namef === '' || namef.search(';') != -1) {
            alert('Nome farmacia vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (namet === '' || namet.search(';') != -1) {
            alert('Nome titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (pass === '' || pass.search(';') != -1 || pass.length < 8) {
            alert('Password titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (indirizzo === '' || indirizzo.search(';') != -1) {
            alert('Indirizzo farmacia vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (telefono === '' || !$.isNumeric(telefono)) {
            alert('Numero telefono non valido (può contenere solo numeri)');
            return false;
        }
        if (user === '' || user.search(';') != -1) {
            alert('Username titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (cognomet === '' || cognomet.search(';') != -1) {
            alert('Cognome titolare vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
    });
});

$(document).ready(function () {
    //NUOVA FARMACIA
    $('#creaop').submit(function () {
        var nameop = $.trim($('#no').val());
        var pass = $.trim($('#pass').val());
        var cognomeop = $.trim($('#co').val());
        var user = $.trim($('#user').val());
        if (nameop === '' || namef.search(';') != -1) {
            alert('Nome operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (pass === '' || pass.search(';') != -1 || pass.length < 8) {
            alert('Password operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (user === '' || user.search(';') != -1) {
            alert('Username operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (cognomeop === '' || cognomet.search(';') != -1) {
            alert('Cognome operatore vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
    });
});
