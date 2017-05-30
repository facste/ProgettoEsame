$(document).ready(function()
    {
        $("#lm").tablesorter( {sortList: [[0,0]]} );
    }
);
$(document).ready(function(){
$("img").click(function() {
    var $tr = $(this).parents("tr");
    var mittente= $tr.find("td").eq(0).html(); // mittente
    var destinatario= $tr.find("td").eq(1).html(); // destinatario
    var messaggio= $tr.find("td").eq(2).html(); // messaggio
    $.post("chat.jsp", {mittente: mittente, destinatario: destinatario, messaggio: messaggio }, function(){
        alert('Riga eliminata con successo');
        location.reload();
    });
});
});