package beans;

import java.io.Serializable;

/**
 * Created by facst on 07/09/2017.
 */
public class ProdottoAcquistato implements Serializable {
    private boolean ricetta;
    private int prodotto;
    private int quantita;

    public ProdottoAcquistato(boolean ricetta, int prodotto, int quantita) {
        this.ricetta = ricetta;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public ProdottoAcquistato() {
    }

    public boolean isRicetta() {
        return ricetta;
    }

    public void setRicetta(boolean ricetta) {
        this.ricetta = ricetta;
    }

    public int getProdotto() {
        return prodotto;
    }

    public void setProdotto(int prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
