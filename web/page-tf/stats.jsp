<%@ page import="util.UtilitàStats" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>

<form method="post" action="allstats.jsp" id="form">
    <label>Data inizio</label><input type="datetime-local" name="inizio">   <label>Data fine</label><input type="datetime-local" name="fine"><br><br>
    <input type="submit" value="Cerca">
        <%UtilitàStats stats= new UtilitàStats();%>
        <%=stats.showStatsTf(request.getParameter("inizio"),request.getParameter("fine"),login.getIdfarmacia())%>
        <%stats.close();%>
<%@ include file="../stili/login/bot.jsp" %>