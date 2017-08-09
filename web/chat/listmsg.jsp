<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 30/05/2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/stili/chat/head.jsp" %>
<%@page import="util.UtilitaMessaggi" %>

<% if(request.getParameter("mittente")!=null) {
    UtilitaMessaggi eliminariga= new UtilitaMessaggi();
    eliminariga.elimina(request.getParameter("mittente"),request.getParameter("destinatario"), request.getParameter("messaggio"));}%>
<% if(request.getParameter("messaggio")!=null) {%>
    <%=request.getParameter("messaggio")%>
<%}else%> <%=""%>
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
        <% UtilitaMessaggi listamessaggi= new UtilitaMessaggi();%>
        <%=listamessaggi.listaMessaggi(login.getUser())%>
        <%listamessaggi.close();%>
        </tbody>
    </table>
</div>
<%@ include file="/stili/chat/bot.jsp" %>