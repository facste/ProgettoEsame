
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestore Farmacie</title>
    <link href="stili/style.css" rel="stylesheet" type="text/css">
    <script src="libraries/jquery.js"></script>
    <script src="libraries/jquery.sticky-kit.js"></script>
</head>
<body>
<div id="logo">
    <img src="images/logo-farmacia-300x336.jpg">
</div>
<div id="menu">
    <ul>
        <li><a href=chisiamo.jsp>Registra farmacia</a></li>
        <li><a href="contatti.jsp">Contatti</a></li>
        <li style="float: right" id="logmenu">
            Benvenuto <%= session.getAttribute("user") %>
            <form action="/logout.do" method="post">
                <input type="submit" value="Logout" id="invio" />
            </form>
        </li>
    </ul>
</div>
</div>
<div id="contenuto">