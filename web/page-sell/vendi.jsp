<%@ page import="util.UtilitaVendita" %><%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 05/09/2017
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>

<div style="overflow-x:auto;" id="log">

    <table id="lm">
        <thead>
        <tr>
            <th>Codice Prodottto</th>
            <th>Nome prodotto</th>
            <%if (!login.getTipo().equals("OB")) %><th>Ricetta</th>
            <th>Prezzo</th>
            <th>Quantità</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <% UtilitaVendita vendita = new UtilitaVendita();%>
        <%=vendita.listaProdotti(login.getIdfarmacia(), login.getTipo())%>
        <%vendita.close();%>
        </tbody>
    </table>
    <input class="vendi" type="button" id="vendi" value="Vendi" style="float:right;">
</div>


<%@ include file="../stili/login/bot.jsp" %>
