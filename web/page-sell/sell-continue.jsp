<%@ page import="util.UtilitaVendita" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>
<jsp:useBean id="acquisto" scope="session" class="beans.ListaAcquisto"/>
<div style="overflow-x:auto;" id="log">
    <form method="post" action="/ricetta.do">
        <table id="lm">
            <thead>
            <tr>
                <th>Codice Prodottto</th>
                <th>Nome prodotto</th>
                <th>COD. Ricetta</th>
            </tr>
            </thead>
            <tbody>
            <% UtilitaVendita vendita = new UtilitaVendita();%>
            <%=vendita.listaRicetta(acquisto)%>
            </tbody>
        </table>
        <input class="ricetta" type="submit" id="ricetta" value="Invia Codici" style="float:right;">
    </form>
</div>
<%@ include file="../stili/login/bot.jsp" %>