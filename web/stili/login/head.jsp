<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestore Farmacie</title>
    <link href="${pageContext.request.contextPath}/stili/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/libraries/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/libraries/jquery.sticky-kit.js"></script>
    <script src="${pageContext.request.contextPath}/libraries/jquery.tablesorter.js"></script>
    <script src="${pageContext.request.contextPath}/utilities/utillogin.js"></script>
</head>
<body>
<jsp:useBean id="login" scope="session" class="beans.LoginData"/>
    <%if (login==null){%>
    <%response.sendRedirect("/index.jsp");%>
    <%}%>
<div id="logo">
    <img src="${pageContext.request.contextPath}/images/logo-farmacia-300x336.png">
</div>
<div id="menu">
    <ul>
        <%if (login.getTipo().equals("REG")) {%>
        <li><a href=${pageContext.request.contextPath}/page-reg/creafarmacia.jsp>Registra Farmacia</a></li>
        <li><a href="${pageContext.request.contextPath}/page-reg/allstats.jsp">Riepilogo Territorio</a></li>
        <li><a href="${pageContext.request.contextPath}/chat/listmsg.jsp">Chat</a></li>
        <%} else if (login.getTipo().equals("TF")) {%>
        <li><a href=${pageContext.request.contextPath}/page-tf/stats.jsp>Statistiche Farmacia</a></li>
        <li><a href=${pageContext.request.contextPath}/page-tf/newoperator.jsp>Registra Operatore</a></li>
        <li><a href="${pageContext.request.contextPath}/page-tf/magazzino.jsp">Magazzino</a></li>
        <li><a href="${pageContext.request.contextPath}/page-sell/vendi.jsp">Vendi</a></li>
        <li><a href="${pageContext.request.contextPath}/chat/listmsg.jsp">Chat</a></li>
        <%} else { %>
        <li><a href=${pageContext.request.contextPath}/page-sell/vendi.jsp>Vendi</a></li>
        <li><a href="${pageContext.request.contextPath}/chat/listmsg.jsp">Chat</a></li>
        <%}%>
        <li style="float: right; padding: 14px 16px;" id="logmenu">
            Benvenuto <%= login.getUser() %>
            <form action="${pageContext.request.contextPath}/logout.do" method="post" style="text-align: center">
                <input type="submit" value="Logout" id="invio"/>
            </form>
        </li>
    </ul>
</div>
<div id="contenuto">