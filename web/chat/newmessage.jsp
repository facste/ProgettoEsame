<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 30/05/2017
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/chat/head.jsp" %>
<div align="center">
    <form action="/creamessaggio.do" method="post" id="creaf">
        <p class="form">
            <label>Destinatario</label> *<input type="text" class="txt" name="dest" id="dest"><br><br>
        </p>
        <p class="form">
        <label>Testo messaggio</label> *<input type="textarea" name="testo" id="" cols="30" rows="10" class="txt" id="testo"></textarea><br><br>
        </p>
        <input type="submit" value="Crea">
        <input type="reset" value="Reset">
    </form>
    <p>* campi obbligatori</p>
</div>
<%@ include file="/stili/chat/bot.jsp" %>