<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/login/head.jsp" %>
<h1>Errore, ritenta</h1>
<p>Codice errore:</p><%=request.getAttribute("errore").toString()%>
<%@ include file="/stili/login/bot.jsp" %>
