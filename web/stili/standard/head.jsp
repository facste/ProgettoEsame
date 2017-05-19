<%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 19/05/2017
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestore Farmacie</title>
    <link href="stili/style.css" rel="stylesheet" type="text/css">
    <script src="libraries/jquery.js"></script>
    <script src="libraries/jquery.sticky-kit.js"></script>
    <script src="utilities/util.js"></script>
</head>
<body>
<div id="logo">
    <img src="images/logo-farmacia-300x336.jpg">
</div>
<div id="menu">
    <ul>
        <li><a href="index.html">Home</a></li>
        <li><a href="index.html">Chi Siamo</a></li>
        <li><a href="index.html">Contatti</a></li>
        <li style="float:right">
            <form class="login" method="post">
                Username <input type="text" class="txt"><br>
                Password <input type="password" maxlength="20" name="psw" class="txt"/><br>
                <input type="submit" id="invio" value="Login">
            </form>
        </li>
    </ul>
</div>
</div>
<div id="contenuto">
