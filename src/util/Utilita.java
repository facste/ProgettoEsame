package util;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by facst on 30/05/2017.
 */
public class Utilita {
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public Utilita() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/esampio.sqlite");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listaMessaggi(String utente)  {
        String query1 = "SELECT * FROM messaggio WHERE destinatario=\""+utente+"\";";
        String query2 = "SELECT * FROM messaggio WHERE mittente=\""+utente+"\";";
        String out="";

        try {
            resultSet = statement.executeQuery(query1);
            while (resultSet.next())
            {
                out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td><td><img src=\"/images/invio.jpg\" ></td></tr>");
            }
            resultSet = statement.executeQuery(query2);
            while (resultSet.next())
            {
                out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td><td><img src=\"/images/ricevuto.jpg\" ></td></tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    public void close(){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

