package actions;

import beans.ListaAcquisto;
import beans.LoginData;
import beans.ProdottoAcquistato;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.DbHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class EndSellAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ListaAcquisto listaRicette = (ListaAcquisto) request.getSession().getAttribute("acquisto");
        String cf = (String) request.getSession().getAttribute("cf");
        LoginData login = (LoginData) request.getSession().getAttribute("login");
        ArrayList<String> ricette = (ArrayList<String>) request.getSession().getAttribute("idricette");
        //lista codici ricette
        boolean fail = false;
        Connection connection;
        ResultSet resultSet;
        PreparedStatement statement = null;
        String query;
        int id;
        try {
            connection= DbHelper.getConn();
            query = "INSERT INTO Acquisto ( data, idpersonale, cfpaziente) VALUES (?,?,?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            Calendar cal = Calendar.getInstance();
            statement.setDate(1, new java.sql.Date(cal.getTime().getTime()));
            statement.setString(2, login.getUser());
            statement.setString(3, (listaRicette.countRicetta() > 0) ? cf : null);
            if (statement.executeUpdate() <= 0) {
                fail = true;
            } else {
                resultSet = statement.getGeneratedKeys();
                id = (Integer) resultSet.getObject(1);
                if (id != -1) {
                    for (ProdottoAcquistato prod : listaRicette) {
                        query = "UPDATE Immagazzina SET quantità= quantità-? WHERE idprodotto=? AND idfarmacia=?";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, prod.getQuantita());
                        statement.setInt(2, prod.getProdotto());
                        statement.setInt(3, login.getIdfarmacia());
                        if (statement.executeUpdate() <= 0) {
                            fail = true;
                            break;
                        }
                        query = "INSERT INTO Composto VALUES (?,?,?)";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, prod.getQuantita());
                        statement.setInt(2, prod.getProdotto());
                        statement.setInt(3, id);
                        if (statement.executeUpdate() <= 0) {
                            fail = true;
                            break;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            fail = true;
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
            request.setAttribute("errore", "Impossibile concludere l'acquisto");
            return (mapping.findForward("error"));
        }
        request.getSession().removeAttribute("idricette");
        request.getSession().removeAttribute("cf");
        return (mapping.findForward("sell-made"));
    }

}
