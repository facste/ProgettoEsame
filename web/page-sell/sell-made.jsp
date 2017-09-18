<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="util.UtilitaVendita" %>
<%@ page import="beans.ProdottoAcquistato" %><%--
  Created by IntelliJ IDEA.
  User: facst
  Date: 07/09/2017
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>
<jsp:useBean id="acquisto" scope="session" class="beans.ListaAcquisto"/>
<div style="overflow-x:auto;" id="log">

    <table id="lm">
        <thead>
        <tr>
            <th>Codice Prodottto</th>
            <th>Nome prodotto</th>
            <th>Prezzo</th>
            <th>Quantità</th>
        </tr>
        </thead>
        <tbody>
        <% UtilitaVendita vendita = new UtilitaVendita();%>
        <%=vendita.listaAcquisto(acquisto)%>
        </tbody>
    </table>
<p style="float: right", font-size:="150%"  >Prezzo: <%= vendita.prezzo(acquisto)%></p>
    <%vendita.close();%>
</div>
<c:remove var="acquisto" scope="session" />
<%@ include file="../stili/login/bot.jsp" %>
