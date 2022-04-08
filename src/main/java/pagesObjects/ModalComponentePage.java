package pagesObjects;

import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import map.ModalComponenteMap;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ModalComponentePage {
    private final ModalComponenteMap mCM = new ModalComponenteMap();
    public int getCountLinhas(){
        return mCM.getListaElementosModal().size();
    }

    public List<String> getListaInfoNomeID(){
        List<String> listaElementosVazios = new ArrayList<>();
        int index = 0;
        for (WebElement info : mCM.getListInfoNomeID()) {
            if (info.getText().length() == 0) {
                listaElementosVazios.add(mCM.getListCabecalhoNomeID().get(index).getText());
            }
            index++;
        }
        return listaElementosVazios;
    }

    public ElementoTexto getTituloModal(){
        return mCM.getSpanTituloModal();
    }

    protected boolean isModalDisplayed() {
        return new ModalComponentePage().getTituloModal().elementoExiste();
    }
}
