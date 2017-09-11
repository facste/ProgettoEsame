package beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by facst on 07/09/2017.
 */
public class ListaAcquisto extends ArrayList<ProdottoAcquistato> implements Serializable{
    public ListaAcquisto() {
    }

    @Override
    public boolean add(ProdottoAcquistato prodottoAcquistato) {
        return super.add(prodottoAcquistato);
    }

    @Override
    public ProdottoAcquistato get(int index) {
        return super.get(index);
    }
}
