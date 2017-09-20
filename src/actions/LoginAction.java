package actions;

import beans.LoginData;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.DbHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Created by facst on 28/04/2017.
 */
public class LoginAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String user = request.getParameter("user");
        boolean fail = false;
        String password = request.getParameter("psw");
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query = "SELECT * FROM Personale WHERE user= ? AND psw=?";
        try {
            connection= DbHelper.getConn();
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                HttpSession session = request.getSession();
                LoginData login = new LoginData();
                login.setIdfarmacia(resultSet.getInt("idfarmacia"));
                login.setUser(user);
                login.setCon(true);
                login.setTipo(resultSet.getString("tipo"));
                session.setAttribute("login", login);

            } else
                fail = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail=true;
        } finally {
            try {
                resultSet.close();
                statement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail)
            return (mapping.findForward("bad-login"));
        request.setAttribute("confirm","Login Eseguito correttamente");
        return (mapping.findForward("success"));

    }
}
