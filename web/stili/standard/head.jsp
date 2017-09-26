
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
    <img src="/images/logo-farmacia-300x336.png">
</div>
<div id="menu">
    <ul>
        <li><a href="/index.jsp">Home</a></li>
        <li><a href=/page-std/chisiamo.jsp>Chi Siamo</a></li>
        <li><a href="/page-std/contatti.jsp">Contatti</a></li>
        <li style="float:right">
            <form action="/login.do" method="post" id="log">
                Username <input type="text" class="txt" id="user" name="user"><br>
                Password <input type="password" maxlength="20" class="txt" id="psw" name="psw"/><br>
                <input type="submit" id="invio" value="Login">
            </form>
        </li>
    </ul>
</div>
<div id="contenuto">
