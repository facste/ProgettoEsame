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
        String query = "SELECT * FROM messaggio WHERE destinatario='"+utente+"' OR mittente='" + utente + "';";
        String out="";

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                if(resultSet.getString("mittente").equals(utente))
                    out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td><td><img src=\"/images/invio.jpg\"></td><td><img src=\"/images/cestino.jpg\" class=\"del\"></td></tr>");
                else
                    out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td><td><img src=\"/images/ricevuto.jpg\"></td><td><img src=\"/images/cestino.jpg\" class=\"del\"></td></tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public boolean elimina(String mittente, String destinatario, String messaggio){
        Messaggio msg= new Messaggio(mittente.substring(mittente.indexOf("<p>") + 3, mittente.indexOf("</p>"))
                                    ,destinatario.substring(destinatario.indexOf("<p>") + 3, destinatario.indexOf("</p>"))
                                    ,messaggio.substring(messaggio.indexOf("<p>") + 3, messaggio.indexOf("</p>")));
        String query = "DELETE FROM messaggio WHERE destinatario = '"+ msg.getDestinatario() + "' AND mittente='" + msg.getMittente() + "' AND testo='" + msg.getTesto()+"';";
        try {
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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

    public String cerca(String user, String msg, String tipo) {
        String query="CREATE VIEW elem AS SELECT * FROM messaggio WHERE destinatario='"+user+"' OR mittente='" + user + "';";
        String out="";
        try {
            statement.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (tipo){
            case "mittente":
                query = "SELECT * FROM elem WHERE mittente LIKE '%" + msg + "%';";
                try {
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                            out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td></tr>");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "destinatario":
                 query= "SELECT * FROM elem WHERE destinatario LIKE '%" + msg + "%';";

                try {
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                        out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td></tr>");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
            case "messaggio":
                query= "SELECT * FROM elem WHERE testo LIKE '%" + msg + "%';";

                try {
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                        out=out.concat("<tr><td><p>"+ resultSet.getString("mittente")+"</p></td><td><p>"+ resultSet.getString("destinatario")+"</p></td><td><p>"+ resultSet.getString("testo")+"</p></td></tr>");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
        }
        return out;
    }

    public String trovatipo(String user){
        String query="SELECT tipo FROM login WHERE user= '" + user +"';";
        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("tipo");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
