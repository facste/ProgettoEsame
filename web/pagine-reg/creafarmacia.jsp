<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>

<h1>Qui potrai aggiungenere delle nuove farmacie</h1> <br>
<form method="post">
    Nome farmacia *<input type="text" class="txt" name="nf" >
    Indirizzo farmacia *<input type="text" class="txt" name="if">
    Telefono *<input type="text" class="txt" name="tel" >
    Nome Titolare *<input type="text" class="txt" name="nametf" >
    Cognome Titolare *<input type="text" class="txt" name="surnametf" >
    <input type="submit" value="Crea">
    <input type="reset" value="Reset">
</form>



<%@ include file="../stili/login/bot.jsp" %>
