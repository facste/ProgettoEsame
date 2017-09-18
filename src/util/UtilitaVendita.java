package util;

import beans.ListaAcquisto;
import beans.ProdottoAcquistato;

import java.math.BigDecimal;
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
            query = "SELECT Prodotto.ID, Prodotto.nome, Prodotto.ricetta, Prodotto.costo, Immagazzina.quantità FROM Prodotto JOIN Immagazzina ON Prodotto.ID = Immagazzina.idprodotto WHERE idfarmacia=? AND quantità>0";
        } else
            query = "SELECT Prodotto.ID, Prodotto.nome, Prodotto.ricetta, Prodotto.costo, Immagazzina.quantità FROM Prodotto JOIN Immagazzina ON Prodotto.ID = Immagazzina.idprodotto WHERE idfarmacia=? AND ricetta!=1 AND quantità>0";
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
                out = out.concat("<td><p>" + resultSet.getBigDecimal(4) + " &#8364</p></td><td>" + resultSet.getInt(5) + "</p></td><td><p><input type=\"text\" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" value=\"0\" class=\"ordina\" readonly=\"readonly\"><input class=\"add\"type=\"button\" id=\"add" + x + "\" value=\"+\"><input class=\"sub\"type=\"button\" id=\"sub" + x++ + "\" value=\"-\">");
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

    public String listaAcquisto(ListaAcquisto acquisto) {
        String query;
        String out = "";
        try {
            for (ProdottoAcquistato prodottoAcquistato : acquisto) {
                query = "SELECT nome,costo FROM Prodotto WHERE ID=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, prodottoAcquistato.getProdotto());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    out = out.concat("<tr><td><p>" + prodottoAcquistato.getProdotto() + "</p></td><td><p>" + resultSet.getString(1) + "</p></td><td><p>" + resultSet.getString(2) + "</p></td><td><p>" + prodottoAcquistato.getQuantita() + "</p></td></tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public String prezzo(ListaAcquisto acquisto) {
        String query;
        double out = 0;
        try {
            for (ProdottoAcquistato prodottoAcquistato : acquisto) {
                query = "SELECT costo FROM Prodotto WHERE ID=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, prodottoAcquistato.getProdotto());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    out += resultSet.getBigDecimal(1).doubleValue() * prodottoAcquistato.getQuantita();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(out).substring(0, 5) + " €";
    }

    public String listaRicetta(ListaAcquisto acquisto) {
        String query;
        String out = "";
        try {
            for (ProdottoAcquistato prodottoAcquistato : acquisto) {
                query = "SELECT nome FROM Prodotto WHERE ID=? AND ricetta=1";
                statement = connection.prepareStatement(query);
                statement.setInt(1, prodottoAcquistato.getProdotto());
                resultSet = statement.executeQuery();
                int x = 0;
                if (resultSet.next()) {
                    out = out.concat("<tr><td><p>" + prodottoAcquistato.getProdotto() + "</p></td><td><p>" + resultSet.getString(1) + "</p></td><td><input type=\"text\" name=\"cr" + x + "\" id=\"cr" + x++ + "\" class=\"cr\" ></td></tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
