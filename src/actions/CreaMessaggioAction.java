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

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by facst on 30/05/2017.
 */
public class CreaMessaggioAction extends Action {
    //DA AGGIUNGERE TRA TF E I SUOI SOTTOPOSTI E TUTTI
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] dest = request.getParameter("dest").split(",");
        String testo = request.getParameter("testo");
        HttpSession session = request.getSession(true);
        LoginData login = (LoginData) session.getAttribute("login");
        String mittente = login.getUser();
        String tipo = login.getTipo();
        Utilita ut = new Utilita();
        boolean fail=false;
        ArrayList<String> tipodest = new ArrayList<String>();
        ArrayList<Boolean> controllo = new ArrayList<Boolean>();
        for (String destinatario : dest) {
            String td = ut.trovatipo(destinatario);
            controllo.add(ut.controlla(mittente, destinatario));
            tipodest.add(td);
            if (td == null) {
                ut.close();
                request.setAttribute("errore", "Impossibile creare messaggio");
                return (mapping.findForward("error"));
            }
        }
        ut.close();
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
            for (int i = 0; i < dest.length; i++) {
                if ((tipodest.get(i).equals("REG") && tipo.equals("TF")) || (tipodest.get(i).equals("TF") && tipo.equals("REG")) && (!dest[i].equals(mittente)) || controllo.get(i)) {

                    query = "INSERT INTO Messaggio(mittente, destinatario, testo) VALUES (?,?,?)";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, mittente);
                    statement.setString(2, dest[i]);
                    statement.setString(3, testo);
                    statement.executeUpdate();
                } else fail = true;
            }
        } catch (Exception e) {
            fail=true;
            e.printStackTrace();
        } finally {
            try {

                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail) {
            request.setAttribute("errore", "Impossibile creare messaggi");
            return (mapping.findForward("error"));
        }
        request.setAttribute("messaggio", "Messaggi creati con successo");
        return (mapping.findForward("success"));

    }
}
