package util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UtilitàMagazzino {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    public UtilitàMagazzino() {
        try {
            connection= DbHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listaProdotti(int farmacia) {
        String query = "SELECT Prodotto.ID, Prodotto.nome, Prodotto.costo, element2.quantità FROM Prodotto LEFT JOIN (SELECT element.ID, element.nome, element.costo, element.quantità FROM (SELECT Prodotto.ID, Prodotto.nome, Prodotto.costo, Immagazzina.quantità, Immagazzina.idfarmacia FROM Prodotto LEFT JOIN Immagazzina ON Prodotto.ID=Immagazzina.idprodotto) AS element WHERE element.idfarmacia=? OR element.idfarmacia ISNULL) AS element2 ON element2.ID= Prodotto.ID";
        String out = "";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, farmacia);
            resultSet = statement.executeQuery();
            int x = 0;
            while (resultSet.next()) {
                out = out.concat("<tr><td><p>" + resultSet.getInt(1) + "</p></td><td><p>" + resultSet.getString(2) + "</p></td><td><p>" + resultSet.getBigDecimal(3) + " &#8364</p></td><td>" + resultSet.getInt(4) + "</p></td><td><p><input type=\"text\" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" value=\"0\" class=\"ordina\" readonly=\"readonly\"><input class=\"add\"type=\"button\" id=\"add" + x + "\" value=\"+\"><input class=\"sub\"type=\"button\" id=\"sub" + x + "\" value=\"-\"><input class=\"ordinare\"type=\"button\" id=\"ord" + x++ + "\" value=\"Ordina\"></td></tr>");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aggiungiordine(String prodotto, String valore, int farmacia) {

        String query = "SELECT Immagazzina.idprodotto FROM Immagazzina WHERE Immagazzina.idfarmacia=? AND Immagazzina.idprodotto=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, farmacia);
            statement.setInt(2, Integer.decode(prodotto));
            resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                //Devo modificare il numero
                query = "UPDATE Immagazzina SET quantità= quantità+? WHERE idprodotto=? AND idfarmacia=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.decode(valore));
                statement.setInt(2, Integer.decode(prodotto));
                statement.setInt(3, farmacia);
                statement.executeUpdate();
            } else {
                //Devo inserire un elemento
                query = "INSERT INTO Immagazzina VALUES (?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.decode(valore));
                statement.setInt(2, farmacia);
                statement.setInt(3, Integer.decode(prodotto));
                statement.executeUpdate();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


