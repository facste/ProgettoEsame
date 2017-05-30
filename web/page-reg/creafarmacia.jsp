<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>

<h1>Qui potrai aggiungenere delle nuove farmacie</h1> <br>
<div align="center">
<form method="post" id="creaf">
    <p class="form">
        <label>Nome farmacia</label> *<input type="text" class="txt" name="nf" id="nf"><br><br>
    </p>
    <p class="form">
    <label>Indirizzo farmacia</label> *<input type="text" class="txt" name="if" id="if"><br><br>
    </p>
    <p class="form">
    <label>Telefono</label> *<input type="text" class="txt" name="tel" id="tel" ><br><br>
    </p>
    <p class="form">
    <label>Nome Titolare</label> *<input type="text" class="txt" name="namet" id="namet"><br><br>
    </p>
    <p class="form">
    <label>Cognome Titolare</label> *<input type="text" class="txt" name="surnamet" id="surnamet"><br><br>
    </p>
    <input type="submit" value="Crea">
    <input type="reset" value="Reset">
</form>
    <p>* campi obbligatori</p>
</div>

<%@ include file="../stili/login/bot.jsp" %>
