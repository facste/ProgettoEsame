package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static Connection c = null;

    public static Connection getConn() throws Exception {
        if (c == null) {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
        }
        return c;
    }

    public static void close() throws SQLException {
        c.close();
        c=null;

    }
}
