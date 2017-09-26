package actions;

import beans.LoginData;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.DbHelper;
import util.UtilitaMessaggi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CreaMessaggioAction extends Action {
    //DA AGGIUNGERE TRA TF E I SUOI SOTTOPOSTI E TUTTI
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dest = request.getParameter("dest");
        String testo = request.getParameter("testo");
        HttpSession session = request.getSession(true);
        LoginData login = (LoginData) session.getAttribute("login");
        String mittente = login.getUser();
        String tipo = login.getTipo();
        UtilitaMessaggi utilitaMessaggi = new UtilitaMessaggi();
        boolean fail = false;
        Connection connection;
        PreparedStatement statement = null;
        String query;
        try {
            connection = DbHelper.getConn();
            boolean giusto = (tipo.equals("REG")) ? (utilitaMessaggi.trovatipo(dest).equals("TF")) : utilitaMessaggi.controlla(mittente, dest);
            if (giusto) {
                query = "INSERT INTO Messaggio(mittente, destinatario, testo) VALUES (?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, mittente);
                statement.setString(2, dest);
                statement.setString(3, testo);
                if (statement.executeUpdate() <= 0)
                    fail = true;
            } else {
                ResultSet resultSet = null;
                if (dest.equalsIgnoreCase("tutti") && tipo.equals("REG")) {
                    query = "SELECT user FROM Personale WHERE tipo='TF'";
                    statement = connection.prepareStatement(query);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        query = "INSERT INTO Messaggio(mittente, destinatario, testo) VALUES (?,?,?)";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, mittente);
                        statement.setString(2, resultSet.getString(1));
                        statement.setString(3, testo);
                        if (statement.executeUpdate() <= 0)
                            fail = true;
                    }
                } else if (dest.equalsIgnoreCase("tutti") && tipo.equals("TF")) {
                    query = "SELECT user FROM Personale WHERE idfarmacia=? AND tipo!='TF'";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, login.getIdfarmacia());
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        query = "INSERT INTO Messaggio(mittente, destinatario, testo) VALUES (?,?,?)";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, mittente);
                        statement.setString(2, resultSet.getString(1));
                        statement.setString(3, testo);
                        if (statement.executeUpdate() <= 0)
                            fail = true;
                    }
                } else if(tipo.equals("TF") && dest.equalsIgnoreCase("regione")){
                    query = "INSERT INTO Messaggio(mittente, destinatario, testo) VALUES (?,?,?)";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, mittente);
                    statement.setString(2, dest);
                    statement.setString(3, testo);
                    if (statement.executeUpdate() <= 0)
                        fail = true;

                }
                else
                    fail = true;
                if (resultSet != null) {
                    resultSet.close();
                }
            }

        } catch (Exception e) {
            fail = true;
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
        if (fail) {
            request.setAttribute("errore", "Impossibile creare messaggi");
            return (mapping.findForward("error"));
        }
        request.setAttribute("messaggio", "Messaggi creati con successo");
        return (mapping.findForward("success"));

    }
}
