package beans;

public class LoginData implements java.io.Serializable {
    private String user;
    private boolean con;
    private String log;
    private int idfarmacia;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getIdfarmacia() {
        return idfarmacia;
    }

    public void setIdfarmacia(int idfarmacia) {
        this.idfarmacia = idfarmacia;
    }

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
