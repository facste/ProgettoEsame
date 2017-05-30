<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if("chat".equals(request.getParameter("fonte"))){%>
    <%@ include file="/stili/chat/head.jsp" %>
    <h1>Messaggio creato con successo</h1>
    <p>Sei loggato come </p><%=session.getAttribute("tipo")%>
    <%@ include file="/stili/chat/bot.jsp" %>
<% }else{ %>
    <%@ include file="/stili/login/head.jsp" %>
    <h1>Login Eseguito correttamente</h1>
    <p>Sei loggato come </p><%=session.getAttribute("tipo")%>
    <%@ include file="/stili/login/bot.jsp" %>
<%}%>