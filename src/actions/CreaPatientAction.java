package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.DbHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class CreaPatientAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String np = request.getParameter("np");
        String cf = (String) request.getSession().getAttribute("cf");
        boolean fail = false;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO Paziente VALUES (?,?)";
        try {
            connection = DbHelper.getConn();
            statement = connection.prepareStatement(query);
            statement.setString(1, cf);
            statement.setString(2, np);
            if (statement.executeUpdate() <= 0) {
                request.setAttribute("errore", "Impossibile creare paziente");
                fail = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail)
            return (mapping.findForward("error"));
        return (mapping.findForward("sell"));
    }
}
