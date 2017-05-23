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
public class MyFirstAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("user");
        String password = request.getParameter("psw");
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/esampio.sqlite");
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT * FROM player WHERE NAME=" + email);
            /*while (resultSet.next())
            {
                System.out.println("EMPLOYEE NAME:"
                        + resultSet.getString("EMPNAME"));
            }*/
            if(statement!=null)
                return(mapping.findForward("success"));
            else
                return(mapping.findForward("bad-user"));
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
/*
        if ((email.trim().length()<1)) {
            return(mapping.findForward("bad-user"));
        } else if ((password == null) ||
                (password.trim().length() < 6)) {
            return(mapping.findForward("bad-password"));
        } else {
            return(mapping.findForward("success"));
        }*/
        return(mapping.findForward("bad-user"));
    }

}
