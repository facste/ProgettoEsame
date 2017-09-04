<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 04/09/2017
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/login/head.jsp" %>
<h1>Qui potrai aggiungenere delle nuove farmacie</h1> <br>
<div align="center">
    <form action="/creaop.do" method="post" id="creaop">
        <p class="form">
            <label>Nome operatore</label> *<input type="text" class="txt" name="no" id="no"><br><br>
        </p>
        <p class="form">
            <label>Cognome operatore</label> *<input type="text" class="txt" name="co" id="co"><br><br>
        </p>
        <p class="form">
        <fieldset>
            <legend>Tipo</legend>
            Operatore di banco <input type="radio" name="tipo" value="OB" checked="checked"/>
            Dottore Farmacista <input type="radio" name="tipo" value="DF"/>
        </fieldset>
        <br><br>
        </p>
        <p class="form">
            <label>Username</label> *<input type="text" class="txt" name="user" id="user"><br><br>
        </p>
        <p class="form">
            <label>Password Titolare</label> *<input type="password" class="txt" name="pass" id="pass"><br><br>
        </p>
        <input type="submit" value="Crea">
        <input type="reset" value="Reset">
    </form>
    <p>* campi obbligatori</p>
</div>
<%@ include file="/stili/login/bot.jsp" %>
