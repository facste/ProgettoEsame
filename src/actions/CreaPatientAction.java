package actions;

import beans.LoginData;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Created by facst on 30/05/2017.
 */
public class CreaPatientAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String np = request.getParameter("np");
        String cf = request.getParameter("cf");
        boolean fail = false;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO Paziente VALUES (?,?)";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
            statement = connection.prepareStatement(query);
            statement.setString(1, cf);
            statement.setString(2, np);
            if (statement.executeUpdate() <= 0) {
                request.setAttribute("errore", "Impossibile creare paziente");
                fail = true;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally
        {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail)
            return (mapping.findForward("error"));
        request.setAttribute("cf", cf);
        return (mapping.findForward("sell"));
    }
}
