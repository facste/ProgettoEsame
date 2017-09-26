package util;

public class Messaggio {
    private String mittente, destinatario;
    private String testo;

    @Override
    public String toString() {
        return "Messaggio{" +
                "mittente='" + mittente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", testo='" + testo + '\'' +
                '}';
    }

    public Messaggio(String mittente, String destinatario, String testo) {
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.testo = testo;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

}
