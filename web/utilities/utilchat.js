$(document).ready(function(){
    //PER ORDINAMENTO COLONNE
        $("#lm").tablesorter( {sortList: [[0,0]]} );
});
$(document).ready(function(){
    //PER ELIMINAZIONE RIGA
    $(".del").click(function() {
        var $tr = $(this).parents("tr");
        var mittente= $tr.find("td").eq(0).html(); // mittente
        var destinatario= $tr.find("td").eq(1).html(); // destinatario
        var messaggio= $tr.find("td").eq(2).html(); // messaggio
        $.post("listmsg.jsp", {mittente: mittente, destinatario: destinatario, messaggio: messaggio }, function(){
            alert('Riga eliminata con successo');
            location.reload();
        });
    });
});

$(document).ready(function(){
    //PER RICERCA
    $('#cercamsg').submit(function () {
        var msg = $.trim($('#namemsg').val());
        if (msg === ''  || msg.search(';') != -1) {
            alert('Ricerca vuota o contenente caratteri illegatli [; ]');
            return false;
        }
    });
});
$(document).ready(function(){
    //PER RICERCA
    $('#creaf').submit(function () {
        var dest = $.trim($('#dest').val());
        var msg = $.trim($('#testo').val());
        if (dest === ''  || dest.search(';') != -1) {
            alert('Destinatario vuoto o contenente caratteri illegatli [; ]');
            return false;
        }
        if (msg === '' ) {
            alert('Messaggio vuoto');
            return false;
        }
    });
});