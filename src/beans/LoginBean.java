package beans;

import org.apache.struts.action.ActionForm;

/**
 * Created by facst on 28/04/2017.
 */
public class LoginBean extends ActionForm {
    private String user, tipo;
    private Boolean log;

    public LoginBean() {
    }

    public LoginBean(String user, String tipo, Boolean log) {

        this.user = user;
        this.tipo = tipo;
        this.log = log;
    }

    public String getUser() {
        return user;

    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getLog() {
        return log;
    }

    public void setLog(Boolean log) {
        this.log = log;
    }
}
