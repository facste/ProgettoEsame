<%@ page import="util.UtilitàMagazzino" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/login/head.jsp" %>
<% if (request.getParameter("prodotto") != null) {
    UtilitàMagazzino aggiungiprodotti = new UtilitàMagazzino();
    aggiungiprodotti.aggiungiordine(request.getParameter("prodotto"), request.getParameter("valore"), login.getIdfarmacia());
    aggiungiprodotti.close();
}%>
<div style="overflow-x:auto;" id="log">

    <table id="lm">
        <thead>
        <tr>
            <th>Codice Prodottto</th>
            <th>Nome prodotto</th>
            <th>Prezzo</th>
            <th>Quantità</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <% UtilitàMagazzino listamagazzino = new UtilitàMagazzino();%>
        <%=listamagazzino.listaProdotti(login.getIdfarmacia())%>
        <%listamagazzino.close();%>
        </tbody>
    </table>

</div>
<%@ include file="/stili/login/bot.jsp" %>

