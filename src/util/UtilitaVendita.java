package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by facst on 05/09/2017.
 */
public class UtilitaVendita {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    public UtilitaVendita() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listaProdotti(int farmacia, String tipo) {
        String query;
        if (!tipo.equals("OB")) {
            query = "SELECT Prodotto.ID, Prodotto.nome, Prodotto.ricetta, Prodotto.costo, Immagazzina.quantità FROM Prodotto JOIN Immagazzina ON Prodotto.ID = Immagazzina.idprodotto WHERE idfarmacia=?";
        } else
            query = "SELECT Prodotto.ID, Prodotto.nome, Prodotto.ricetta, Prodotto.costo, Immagazzina.quantità FROM Prodotto JOIN Immagazzina ON Prodotto.ID = Immagazzina.idprodotto WHERE idfarmacia=? AND ricetta!=1 ";
        String out = "";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, farmacia);
            resultSet = statement.executeQuery();
            int x = 0;
            while (resultSet.next()) {
                out = out.concat("<tr><td><p>" + resultSet.getInt(1) + "</p></td><td><p>" + resultSet.getString(2) + "</p></td>");
                if (!tipo.equals("OB"))
                    out = out.concat("<td><p>" + resultSet.getBigDecimal(3) + "</p></td>");
                out = out.concat("<td><p>" + resultSet.getBigDecimal(4) + " &#8364</p></td><td>" + resultSet.getInt(5) + "</p></td><td><p><input type=\"text\" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" value=\"0\" class=\"ordina\"><input class=\"add\"type=\"button\" id=\"add" + x + "\" value=\"+\"><input class=\"sub\"type=\"button\" id=\"sub" + x++ + "\" value=\"-\">");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public void close() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void acaso(Object id, Object quantità){
        int[]q= (int[]) quantità;
        String[]idp= (String[]) quantità;
        System.out.println(idp.toString());
    }
}
