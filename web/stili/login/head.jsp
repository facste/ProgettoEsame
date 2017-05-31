
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestore Farmacie</title>
    <link href="/stili/style.css" rel="stylesheet" type="text/css">
    <script src="/libraries/jquery.js"></script>
    <script src="/libraries/jquery.sticky-kit.js"></script>
    <script src="/utilities/util.js"></script>
</head>
<body>
<div id="logo">
    <img src="/images/logo-farmacia-300x336.jpg">
</div>
<div id="menu">
    <ul>
        <%if(session.getAttribute("tipo").equals("REG")){%>
            <li><a href=/page-reg/creafarmacia.jsp>Registra Farmacia</a></li>
            <li><a href="">Riepilogo Territorio</a></li>
            <li><a href="/chat/listmsg.jsp">Chat</a></li>
        <%}else if(session.getAttribute("tipo").equals("TF")){%>
            <li><a href=/page-reg/creafarmacia.jsp>Statistiche Farmacia</a></li>
            <li><a href=/page-reg/creafarmacia.jsp>Registra Operatore</a></li>
            <li><a href="contatti.jsp">Magazzino</a></li>
            <li><a href="">Vendi</a></li>
            <li><a href="/chat/listmsg.jsp">Chat</a></li>
        <%}else{ %>
            <li><a href=/page-reg/creafarmacia.jsp>Vendi</a></li>
            <li><a href="/chat/listmsg.jsp">Chat</a></li>
        <%}%>
            <li style="float: right; padding: 14px 16px;" id="logmenu">
            Benvenuto <%= session.getAttribute("user") %>
            <form action="/logout.do" method="post" style="text-align: center">
                <input type="submit" value="Logout" id="invio" />
            </form>
        </li>
    </ul>
</div>
<div id="contenuto">