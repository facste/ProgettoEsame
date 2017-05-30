$(document).ready(function()
    {
        $("#lm").tablesorter( {sortList: [[0,0]]} );
    }
);
$(document).ready(function(){
$("img").click(function() {
    var $tr = $(this).parents("tr");
    alert( $tr.find("td").eq(0).html() ); // mittente
    alert( $tr.find("td").eq(1).html() ); // destinatario
    alert( $tr.find("td").eq(2).html() ); // messaggio
});
});