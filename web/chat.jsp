<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 30/05/2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/stili/chat/head.jsp" %>
<%@page import="util.Utilità" %>
<table>
    <tr><td>Mittente</td><td>Destinatario</td><td>Messaggio</td><td>I/R</td></tr>
    <% new Utilità().listaMessaggi(session.getAttribute("user").toString());%>
<%@ include file="/stili/chat/bot.jsp" %>