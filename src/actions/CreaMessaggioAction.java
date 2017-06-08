package actions;

import beans.LoginData;
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
import java.util.ArrayList;

/**
 * Created by facst on 30/05/2017.
 */
public class CreaMessaggioAction extends Action {
    //DA AGGIUNGERE TRA TF E I SUOI SOTTOPOSTI E TUTTI
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] dest=request.getParameter("dest").split(",");
        String testo=request.getParameter("testo") ;
        HttpSession session = request.getSession(true);
        LoginData login= (LoginData) session.getAttribute("login");
        String mittente= login.getUser();
        String tipo=login.getTipo();
        Utilita ut=new Utilita();
        ArrayList<String> tipodest=new ArrayList<String>();
        for(String destinatario: dest) {
            String td= ut.trovatipo(destinatario);
            tipodest.add(td);
            if (td == null) {
                ut.close();
                request.setAttribute("errore", "Impossibile creare messaggio");
                return (mapping.findForward("error"));
            }
        }
        ut.close();
            Connection connection = null;
            Statement statement = null;
            String query="";
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
                statement = connection.createStatement();
                for (int i = 0; i < dest.length; i++) {
                    if ((tipodest.get(i).equals("REG") && tipo.equals("TF")) || (tipodest.get(i).equals("TF") && tipo.equals("REG")) && (!dest[i].equals(mittente))) {
                        query=query.concat("INSERT INTO messaggio(mittente, destinatario, testo) VALUES ('" + mittente + "','" + dest[i] + "','" + testo + "');");
                    }
                    else {
                        statement.close();
                        connection.close();
                        request.setAttribute("errore","Impossibile creare messaggio");
                        return(mapping.findForward("error"));
                    }
                }
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

            request.setAttribute("errore","Impossibile creare messaggio");
            return(mapping.findForward("error"));

    }
}
