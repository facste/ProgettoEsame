<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 30/05/2017
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/chat/head.jsp" %>
<%@page import="util.Utilita" %>
<%String msg= request.getParameter("cercamsg");
String tipo= request.getParameter("tipo");%>

<div style="overflow-x:auto;">
    <table id="lm"  class="tablesorter">
        <thead>
        <tr><th>Mittente</th><th>Destinatario</th><th>Messaggio</th></tr>
        </thead>
        <tbody>
        <% Utilita listamessaggi= new Utilita();%>
        <%=listamessaggi.cerca(login.getUser(),msg,tipo)%>
        <%listamessaggi.close();%>
        </tbody>
    </table>
</div>
<%@ include file="/stili/chat/bot.jsp" %>