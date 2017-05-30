<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 30/05/2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/stili/chat/head.jsp" %>
<%@page import="util.Utilita" %>
<div style="overflow-x:auto;">
    <table id="lm">
        <tr><th>Mittente</th><th>Destinatario</th><th>Messaggio</th><th>I/R</th></tr>
        <% Utilita listamessaggi= new Utilita();%>
        <%=listamessaggi.listaMessaggi(session.getAttribute("user").toString())%>
        <%listamessaggi.close();%>
    </table>
</div>
<%@ include file="/stili/chat/bot.jsp" %>