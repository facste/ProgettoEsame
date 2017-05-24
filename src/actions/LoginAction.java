package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by facst on 28/04/2017.
 */
public class LoginAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String user = request.getParameter("user");
        String password = request.getParameter("psw");
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String query="SELECT * FROM login WHERE user= \"" + user + "\" AND password= \"" + password + "\";";
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/esampio.sqlite");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet!=null){
                resultSet.close();
                statement.close();
                connection.close();
                return(mapping.findForward("success"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return(mapping.findForward("bad-login"));
    }
}
