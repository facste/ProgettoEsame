package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.Utilita;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

/**
 * Created by facst on 30/05/2017.
 */
public class CreaMessaggioAction extends Action {
    //DA AGGIUNGERE TRA TF E I SUOI SOTTOPOSTI ECC
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dest=request.getParameter("dest");
        String testo=request.getParameter("testo") ;
        HttpSession session = request.getSession();
        String mittente= session.getAttribute("user").toString();
        String tipo=session.getAttribute("tipo").toString();
        Utilita ut=new Utilita();
        String tipodest= ut.trovatipo(dest);
        ut.close();
        if((tipodest.equals("REG")&& tipo.equals("TF")) || (tipodest.equals("TF")&& tipo.equals("REG"))&&(!dest.equals(mittente))) {
            Connection connection = null;
            Statement statement = null;
            String query="INSERT INTO messaggio(mittente, destinatario, testo) VALUES ('" +mittente+ "','" +dest+ "','" +testo+ "');";
            try
            {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
                statement = connection.createStatement();
                statement.executeUpdate(query);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    statement.close();
                    connection.close();
                    request.setAttribute("messaggio","Messaggio creato con successo");
                    return(mapping.findForward("success"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
            request.setAttribute("errore","Impossibile creare messaggio");
            return(mapping.findForward("error"));

    }
}
