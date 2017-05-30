package util;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by facst on 30/05/2017.
 */
public class Utilità {
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public Utilità() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/esampio.sqlite");
            statement = connection.createStatement();
        } catch (Exception e) {
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
    }

    public void listaMessaggi(String utente)  {
        String query1 = "SELECT * FROM messaggio WHERE destinatario=\""+utente+"\";";
        String query2 = "SELECT * FROM messaggio WHERE mittente=\""+utente+"\";";
        try {
            resultSet = statement.executeQuery(query1);
            while (resultSet.next())
            {
                System.out.println("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("messaggio")+"</p></td><td>img src=\"/images/invio.jpg\" ></td></tr>");
            }
            resultSet = statement.executeQuery(query2);
            while (resultSet.next())
            {
                System.out.println("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("messaggio")+"</p></td><td>img src=\"/images/ricevuto.jpg\" ></td></tr>");
            }

        } catch (Exception e) {
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


    }
}

