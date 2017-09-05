package actions;

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
public class CreaFarmaciaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nf = request.getParameter("nf");
        String indirizzof = request.getParameter("if");
        String tel = request.getParameter("tel");
        String namet = request.getParameter("namet");
        String surname = request.getParameter("cognomet");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        boolean fail = false;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query = "SELECT * FROM Personale WHERE user= ? AND tipo='TF'";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                query = "INSERT INTO Farmacia (indirizzo, nome, numero) VALUES (?,?,?)";
                statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, indirizzof);
                statement.setString(2, nf);
                statement.setString(3, tel);
                ResultSet result = null;
                int id = -1;
                if (statement.executeUpdate() > 0)
                    result = statement.getGeneratedKeys();
                if (result != null && result.next())
                    id = (Integer) result.getObject(1);
                if (id != -1) {
                    query = "INSERT INTO Personale VALUES (?,?,?,?,?,?)";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, namet);
                    statement.setString(2, surname);
                    statement.setString(3, pass);
                    statement.setString(4, user);
                    statement.setString(5, "TF");
                    statement.setInt(6, id);
                    if (statement.executeUpdate() <= 0) {
                        request.setAttribute("errore", "Impossibile creare titolare");
                        fail = true;
                    }

                } else {
                    request.setAttribute("errore", "Impossibile creare farmacia");
                    fail = true;
                }
            } else {
                request.setAttribute("errore", "Titolare Farmacia già esistente");
                fail = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Impossibile creare farmacia");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail)
            return (mapping.findForward("bad-farmacia"));
        request.setAttribute("confirm", "Creazione Farmacia Eseguita correttamente");
        return (mapping.findForward("success"));
    }
}
