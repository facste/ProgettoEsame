package actions;

import beans.LoginData;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.DbHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class CreaOperatoreAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("no");
        String surname = request.getParameter("co");
        String tipo = request.getParameter("tipo");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        boolean fail = false;
        Connection connection;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query = "SELECT * FROM Personale WHERE user= ? AND (tipo='OB' OR tipo='DF') ";
        try {
            connection= DbHelper.getConn();
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                query = "INSERT INTO Personale VALUES (?,?,?,?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, surname);
                statement.setString(3, pass);
                statement.setString(4, user);
                statement.setString(5, tipo);
                LoginData login = (LoginData) request.getSession().getAttribute("login");
                int id = login.getIdfarmacia();
                statement.setInt(6, id);
                if (statement.executeUpdate() <= 0) {
                    request.setAttribute("errore", "Impossibile creare operatore");
                    fail = true;
                }

            } else {
                request.setAttribute("errore", "Operatore Farmacia già esistente");
                fail = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Impossibile aggiungere operatore");
        } finally

        {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail)
            return (mapping.findForward("bad-operator"));
        request.setAttribute("confirm", "Aggiunta operatore eseguita correttamente");
        return (mapping.findForward("success"));
    }
}
