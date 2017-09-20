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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query;
        int id;
        int i = 0;
        try {
            connection= DbHelper.getConn();
            query = "INSERT INTO Acquisto ( data, idpersonale, cfpaziente) VALUES (?,?,?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            statement.setString(1, dateFormat.format(cal.getTime()));
            statement.setString(2, login.getUser());
            statement.setString(3, (listaRicette.countRicetta() > 0) ? cf : null);
            if (statement.executeUpdate() <= 0) {
                fail = true;
            } else {
                resultSet = statement.getGeneratedKeys();
                id = (Integer) resultSet.getObject(1);
                if (id != -1) {
                    for (ProdottoAcquistato prod : listaRicette) {
                        if (prod.isRicetta()) {
                            query = "INSERT INTO ObbligoRicetta VALUES (?,?)";
                            statement = connection.prepareStatement(query);
                            statement.setInt(1, id);
                            statement.setInt(2, Integer.decode(ricette.get(i++)));
                            if (statement.executeUpdate() <= 0) {
                                fail = true;
                                break;
                            }
                        }
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            fail = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();

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
