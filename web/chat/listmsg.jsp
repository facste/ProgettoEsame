<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 30/05/2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/stili/chat/head.jsp" %>
<%@page import="util.Utilita" %>

<% if(request.getParameter("mittente")!=null) {
    Utilita eliminariga= new Utilita();
    eliminariga.elimina(request.getParameter("mittente"),request.getParameter("destinatario"), request.getParameter("messaggio"));}%>

<form method="post" action="search.jsp" id="form">
    <label>Cerca</label><input type="text" class="txt" name="cercamsg" id="namemsg">
    <input type="radio" name="tipo" value="mittente" checked> Mittente
    <input type="radio" name="tipo" value="destinatario"> Destinatario
    <input type="radio" name="tipo" value="messaggio"> Messaggio
    <input type="submit" value="Cerca" id="cercamsg" class="invio">
</form>

<div style="overflow-x:auto;">
        <table id="lm"  class="tablesorter">
        <thead>
        <tr><th>Mittente</th><th>Destinatario</th><th>Messaggio</th><th>I/R</th><th>Elimina</th></tr>
        </thead>
        <tbody>
        <% Utilita listamessaggi= new Utilita();%>
        <%=listamessaggi.listaMessaggi(session.getAttribute("user").toString())%>
        <%listamessaggi.close();%>
        </tbody>
    </table>
</div>
<%@ include file="/stili/chat/bot.jsp" %>