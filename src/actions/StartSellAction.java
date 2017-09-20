package actions;

import beans.ListaAcquisto;
import beans.LoginData;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import beans.ProdottoAcquistato;
import util.DbHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by facst on 28/04/2017.
 */
public class StartSellAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        int[] idprodotti = Arrays.stream(request.getParameterValues("prodotti")).mapToInt(Integer::parseInt).toArray();
        int[] quantita = Arrays.stream(request.getParameterValues("quantita")).mapToInt(Integer::parseInt).toArray();
        LoginData login = (LoginData) request.getSession().getAttribute("login");
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        ListaAcquisto prodottiAcquistati = new ListaAcquisto();
        boolean fail = false;
        String query;
        int result = -1;
        //id acquisto;
        int id = -1;
        boolean richiedeRicetta = false;
        try {
            connection= DbHelper.getConn();
            //Controllo che non ci siano ricette nell'acquisto
            query = "SELECT ID,ricetta FROM Prodotto WHERE ricetta=1";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            HashMap<Integer, Integer> hashMap = new HashMap<>(); //HashMap prodotto- ricetta
            while (resultSet.next()) {
                hashMap.put(resultSet.getInt(1), resultSet.getInt(2));
            }
            for (int i = 0; i < quantita.length; i++) {
                Integer value = hashMap.get(idprodotti[i]);
                if (value != null)
                    richiedeRicetta = true;
                prodottiAcquistati.add(new ProdottoAcquistato((value != null), idprodotti[i], quantita[i]));
            }

            if (!richiedeRicetta) {
                //NUOVO ACQUISTO
                query = "INSERT INTO Acquisto ( data, idpersonale, cfpaziente) VALUES (?,?,NULL )";
                statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                statement.setString(1, dateFormat.format(cal.getTime()));
                statement.setString(2, login.getUser());
                result = statement.executeUpdate();
                if (result <= 0) {
                    fail = true;
                } else {
                    resultSet = statement.getGeneratedKeys();
                    id = (Integer) resultSet.getObject(1);
                    if (id != -1) {
                        if (resultSet != null && resultSet.next())
                            for (int i = 0; i < idprodotti.length; i++) {
                                //SOTTRAGGO DAL MAGAZZINO
                                query = "UPDATE Immagazzina SET quantità= quantità-? WHERE idprodotto=? AND idfarmacia=?";
                                statement = connection.prepareStatement(query);
                                statement.setInt(1, quantita[i]);
                                statement.setInt(2, idprodotti[i]);
                                statement.setInt(3, login.getIdfarmacia());
                                result = statement.executeUpdate();
                                if (result <= 0) {
                                    fail = true;
                                    break;
                                }
                                //INSERICO NELLOSTORICO
                                query = "INSERT INTO Composto VALUES (?,?,?)";
                                statement = connection.prepareStatement(query);
                                statement.setInt(1, quantita[i]);
                                statement.setInt(2, idprodotti[i]);
                                statement.setInt(3, id);
                                if (statement.executeUpdate() <= 0) {
                                    fail = true;
                                    break;
                                }

                            }
                    } else
                        fail = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Tipo di return
        response.addHeader("Content-Type", "text/plain");
        response.setContentType("text/plain; charset=windows-1252");
        try {
            if (fail) {
                response.getOutputStream().print("error");
            } else {
                request.getSession().setAttribute("acquisto", prodottiAcquistati);
                if (!richiedeRicetta) {
                    response.getOutputStream().print("sell-made");
                } else {
                    response.getOutputStream().print("sell-continue");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
