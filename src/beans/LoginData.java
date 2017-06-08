package beans;

/**
 * Created by facst on 08/06/2017.
 */
public class LoginData implements java.io.Serializable {
    private String user;
    private boolean con;
    private String log;

    public String getUser() {
        return user;
    }

    public LoginData() {
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isCon() {
        return con;
    }

    public void setCon(boolean con) {
        this.con = con;
    }

    public String getTipo() {
        return log;
    }

    public void setTipo(String tipo) {
        this.log = tipo;
    }

    public LoginData(String user, String tipo, boolean con) {
        this.user = user;
        this.con = con;
        this.log = tipo;
    }
}
