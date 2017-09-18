package actions;

import beans.ListaAcquisto;
import beans.LoginData;
import beans.ProdottoAcquistato;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by facst on 28/04/2017.
 */
public class RicettaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ListaAcquisto listaRicette = ((ListaAcquisto) request.getSession().getAttribute("acquisto")).ricettaElement();
        //lista codici ricette
        ArrayList<String> codRicette = new ArrayList<>();
        for (int i = 0; i < listaRicette.size(); i++)
            codRicette.add(request.getParameter("cr" + i));
        boolean diffpatient = false, insertpatient = false;
        String cf = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query;
        //Controllo che tutti gli id sia di unico paziente
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
            for (int i = 0; i < listaRicette.size(); i++) {
                query = "SELECT cf FROM Ricetta WHERE ID=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.decode(codRicette.get(i)));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (cf == null)
                        cf = resultSet.getString(1);
                    else if (cf.equals(resultSet.getString(1)))
                        diffpatient = true;
                }
            }
            if (diffpatient)
                request.setAttribute("errore", "Puoi usare un solo paziente alla volta");
            else {
                query = "SELECT count(*) FROM Paziente WHERE CF=?";
                statement = connection.prepareStatement(query);
                statement.setString(1, cf);
                resultSet = statement.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) == 0)
                    insertpatient = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (diffpatient)
            return (mapping.findForward("error"));
        request.setAttribute("cf", cf);
        request.getSession().setAttribute("idricette", codRicette);
        if (insertpatient)
            return (mapping.findForward("insert-patient"));
        return (mapping.findForward("sell"));
    }
}
