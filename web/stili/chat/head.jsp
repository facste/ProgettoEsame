
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestore Farmacie</title>
    <link href="/stili/style.css" rel="stylesheet" type="text/css">
    <script src="/libraries/jquery.js"></script>
    <script src="/libraries/jquery.sticky-kit.js"></script>
    <script src="/libraries/jquery.tablesorter.js"></script>
    <script src="/utilities/utilchat.js"></script>
</head>
<body>
<jsp:useBean id="login" scope="session" class="beans.LoginData" />
    <% if(!login.isCon())
        response.sendRedirect("/index.jsp");%>
<div id="logo">
    <img src="/images/logo-farmacia-300x336.jpg">
</div>
<div id="menu">
    <ul>
        <li><a href="/chat/newmessage.jsp">Home</a></li>
        <li><a href=/chat/newmessage.jsp>Scrivi messaggio</a></li>
        <li><a href="/chat/listmsg.jsp">Lista messaggi</a></li>
        <li style="float: right; padding: 14px 16px;" id="logmenu">
            Benvenuto <%= login.getUser() %>
            <form action="/logout.do" method="post" style="text-align: center">
                <input type="submit" value="Logout" id="invio" />
            </form>
        </li>
    </ul>
</div>
<div id="contenuto">