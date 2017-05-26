<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/login/head.jsp" %>

<h1>Login Eseguito correttamente</h1>
<p>Ti sei loggato come </p><%=session.getAttribute("tipo")%>

<%@ include file="/stili/login/bot.jsp" %>
