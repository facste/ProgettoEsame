<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/stili/login/head.jsp" %>
<h1><%=request.getAttribute("confirm").toString()%></h1>
<p>Sei loggato come </p><%=login.getUser()%>
<%@ include file="/stili/login/bot.jsp" %>