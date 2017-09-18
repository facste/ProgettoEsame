<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../stili/login/head.jsp" %>
<div style="overflow-x:auto;" id="log">
    <form method="post" action="/creapat.do" id="creapat">
        <p class="form">
            <label>Nome</label> *<input type="text" class="txt" name="np" ipd="np"><br><br>
        </p>
        <p class="form">
            <label>CF</label> *<input type="text" class="txt" name="cf" id="cf" value="<%=request.getAttribute("cf")%>"
                                      readonly="readonly"><br><br>
        </p>
        <input class="paziente" type="submit" id="paziente" value="Crea Paziente">
    </form>
</div>
<%@ include file="../stili/login/bot.jsp" %>