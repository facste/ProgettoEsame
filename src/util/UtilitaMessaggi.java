package util;

import java.sql.*;


public class UtilitaMessaggi {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    public UtilitaMessaggi() {
        try {
            connection = DbHelper.getConn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listaMessaggi(String utente) {
        String query = "SELECT * FROM Messaggio WHERE destinatario=? OR mittente=?";
        String out = "";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, utente);
            statement.setString(2, utente);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("mittente").equals(utente))
                    out = out.concat("<tr><td><p>" + resultSet.getString("mittente") + "</p></td><td><p>" + resultSet.getString("destinatario") + "</p></td><td><p>" + resultSet.getString("testo") + "</p></td><td><img src=\"/images/invio.png\"></td><td><img src=\"/images/cestino.png\" class=\"del\"></td></tr>");
                else
                    out = out.concat("<tr><td><p>" + resultSet.getString("mittente") + "</p></td><td><p>" + resultSet.getString("destinatario") + "</p></td><td><p>" + resultSet.getString("testo") + "</p></td><td><img src=\"/images/ricevuto.png\"></td><td><img src=\"/images/cestino.png\" class=\"del\"></td></tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public void elimina(String mittente, String destinatario, String messaggio) {
        Messaggio msg = new Messaggio(mittente.substring(mittente.indexOf("<p>") + 3, mittente.indexOf("</p>"))
                , destinatario.substring(destinatario.indexOf("<p>") + 3, destinatario.indexOf("</p>"))
                , messaggio.substring(messaggio.indexOf("<p>") + 3, messaggio.indexOf("</p>")));
        String query = "DELETE FROM Messaggio WHERE destinatario = ? AND mittente=? AND testo=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, msg.getDestinatario());
            statement.setString(2, msg.getMittente());
            statement.setString(3, msg.getTesto());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String cerca(String user, String msg, String tipo) {
        String query = null;
        String out = "";
        switch (tipo) {
            case "mittente":
                query = "SELECT * FROM Messaggio WHERE mittente LIKE ? AND (destinatario=? OR mittente=?) ";
                break;
            case "destinatario":
                query = "SELECT * FROM Messaggio WHERE destinatario LIKE ? AND (destinatario=? OR mittente=?)";
                break;
            case "messaggio":
                query = "SELECT * FROM Messaggio WHERE testo LIKE ? AND (destinatario=? OR mittente=?)";
                break;
        }
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + msg + "%");
            statement.setString(2, user);
            statement.setString(3, user);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                out = out.concat("<tr><td><p>" + resultSet.getString("mittente") + "</p></td><td><p>" + resultSet.getString("destinatario") + "</p></td><td><p>" + resultSet.getString("testo") + "</p></td></tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    public String trovatipo(String user) {
        String query = "SELECT tipo FROM Personale WHERE user=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            resultSet = statement.executeQuery();
            return (resultSet.next()) ? resultSet.getString(1) : "null";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }

    //Controlla che due user siano nella stessa farmacia
    public boolean controlla(String x, String y) {
        String query = "SELECT idfarmacia FROM Personale WHERE user= ?";


        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, x);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                x = String.valueOf(resultSet.getInt("idfarmacia"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        query = "SELECT idfarmacia FROM Personale WHERE user= ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, y);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                y = String.valueOf(resultSet.getInt("idfarmacia"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return y.equals(x);
    }
}

