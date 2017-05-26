<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/standard/head.jsp" %>

<h1>Login Eseguito correttamente</h1>
<p>Benvenuto</p><%= session.getAttribute("user")%>
<%@ include file="/stili/standard/bot.jsp" %>
