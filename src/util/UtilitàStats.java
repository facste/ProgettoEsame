package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by facst on 20/09/2017.
 */
public class UtilitàStats {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    public UtilitàStats() {
        try {

            connection= DbHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showStatsAll(String inizio, String fine){
        String query="";
        if(inizio==null && fine==null)
            query = "SELECT nome,costo FROM Acquisto WHERE ID=?";
        /*statement = connection.prepareStatement(query);
        statement.setInt(1, prodottoAcquistato.getProdotto());
        resultSet = statement.executeQuery();*/
        return null;
    }
}
