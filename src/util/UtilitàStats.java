package util;

import beans.LoginData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by facst on 20/09/2017.
 */
public class UtilitàStats {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    public UtilitàStats() {
        try {

            connection = DbHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showStatsAll(String inizio, String fine) {
        String start = (inizio == null) ? null : inizio.replace("T", " ");
        String end = (fine == null) ? null : fine.replace("T", " ");
        String out = "";
        String query = "";
        if (start == null || end == null)
            query = "SELECT\n" +
                    "  (SELECT count(Acquisto.CODICE)" +
                    "   FROM Acquisto" +
                    "  )AS Acquisti," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto" +
                    "  )AS Quantità," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto" +
                    "     JOIN Prodotto ON Composto.idprodotto = Prodotto.ID" +
                    "   WHERE ricetta = 1) AS Ricette;";
        else
            query = "SELECT" +
                    "  (SELECT count(Acquisto.CODICE)" +
                    "   FROM Acquisto" +
                    "   WHERE data BETWEEN ? AND ?" +
                    "  ) AS Acquisti," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto" +
                    "     JOIN Acquisto" +
                    "       ON Composto.idacquisto = Acquisto.CODICE" +
                    "   WHERE data BETWEEN ? AND ?" +
                    "  ) AS Quantità," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto" +
                    "     JOIN Prodotto ON Composto.idprodotto = Prodotto.ID" +
                    "     JOIN Acquisto ON Composto.idacquisto = Acquisto.CODICE" +
                    "   WHERE ricetta = 1 AND (data BETWEEN ? AND ?)" +
                    "  ) AS Ricette;";
        try {
            statement = connection.prepareStatement(query);
            if (start != null && end != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                statement.setDate(1, new java.sql.Date(formatter.parse(start).getTime()));
                statement.setDate(2, new java.sql.Date(formatter.parse(end).getTime()));
                statement.setDate(3, new java.sql.Date(formatter.parse(start).getTime()));
                statement.setDate(4, new java.sql.Date(formatter.parse(end).getTime()));
                statement.setDate(5, new java.sql.Date(formatter.parse(start).getTime()));
                statement.setDate(6, new java.sql.Date(formatter.parse(end).getTime()));

            }
            resultSet = statement.executeQuery();
            resultSet.next();
            out = "<p><b>Acquisti: </b>" + resultSet.getInt(1) + "</p><br><p><b>Quantità: </b>" + resultSet.getInt(2) + "</p><br><p><b>Numero Ricette: </b>" + resultSet.getInt(3) + "</p><br>";

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return out;
    }

    public void close() {
        try {
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showStatsTf(String inizio, String fine, int idfarmacia) {
        String start = (inizio == null) ? null : inizio.replace("T", " ");
        String end = (fine == null) ? null : fine.replace("T", " ");
        String out = "";
        String query = "";
        if (start == null || end == null)
            query = "SELECT\n" +
                    "  (SELECT count(Acquisto.CODICE)" +
                    "   FROM Acquisto" +
                    "     JOIN Personale ON Acquisto.idpersonale = Personale.user" +
                    "   WHERE idfarmacia = ?" +
                    "  )AS Acquisti," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto JOIN Acquisto ON Composto.idacquisto = Acquisto.CODICE" +
                    "    JOIN Personale ON Acquisto.idpersonale = Personale.user" +
                    "   WHERE idfarmacia = ?" +
                    "  )AS Quantità," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto JOIN Acquisto ON Composto.idacquisto = Acquisto.CODICE" +
                    "     JOIN Personale ON Acquisto.idpersonale = Personale.user" +
                    "     JOIN Prodotto ON Composto.idprodotto = Prodotto.ID" +
                    "   WHERE ricetta = 1 AND idfarmacia=?) AS Ricette;";
        else
            query = "SELECT\n" +
                    "  (SELECT count(Acquisto.CODICE)" +
                    "   FROM Acquisto" +
                    "     JOIN Personale ON Acquisto.idpersonale = Personale.user" +
                    "   WHERE data BETWEEN ? AND ? AND idfarmacia = ?" +
                    "  ) AS Acquisti," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto" +
                    "     JOIN Acquisto" +
                    "       ON Composto.idacquisto = Acquisto.CODICE" +
                    "     JOIN Personale ON Acquisto.idpersonale = Personale.user" +
                    "   WHERE (data BETWEEN ? AND ?) AND idfarmacia = ?" +
                    "  ) AS Quantità," +
                    "  (SELECT SUM(Composto.quantità)" +
                    "   FROM Composto" +
                    "     JOIN Prodotto ON Composto.idprodotto = Prodotto.ID" +
                    "     JOIN Acquisto ON Composto.idacquisto = Acquisto.CODICE" +
                    "     JOIN Personale ON Acquisto.idpersonale = Personale.user" +
                    "   WHERE ricetta = 1 AND (data BETWEEN ? AND ?) AND idfarmacia = ?" +
                    "  ) AS Ricette;";
        try {
            statement = connection.prepareStatement(query);
            if (start != null && end != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                statement.setDate(1, new java.sql.Date(formatter.parse(start).getTime()));
                statement.setDate(2, new java.sql.Date(formatter.parse(end).getTime()));
                statement.setInt(3,idfarmacia);
                statement.setDate(4, new java.sql.Date(formatter.parse(start).getTime()));
                statement.setDate(5, new java.sql.Date(formatter.parse(end).getTime()));
                statement.setInt(6,idfarmacia);
                statement.setDate(7, new java.sql.Date(formatter.parse(start).getTime()));
                statement.setDate(8, new java.sql.Date(formatter.parse(end).getTime()));
                statement.setInt(9,idfarmacia);

            }
            resultSet = statement.executeQuery();
            resultSet.next();
            out = "<p><b>Acquisti: </b>" + resultSet.getInt(1) + "</p><br><p><b>Quantità: </b>" + resultSet.getInt(2) + "</p><br><p><b>Numero Ricette: </b>" + resultSet.getInt(3) + "</p><br>";

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return out;
    }
}
