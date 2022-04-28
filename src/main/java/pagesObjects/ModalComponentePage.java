package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import map.ModalComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static support.Utils.waitLoadPage;

public class ModalComponentePage extends Pagina {
    private final ModalComponenteMap mCM = new ModalComponenteMap();

    public int getCountLinhas() {
        return mCM.getListaElementosModal().size();
    }

    public List<String> getListaInfoNomeID() {
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

    protected boolean isModalDisplayed() {
        //Estava falhando ao adicionar membro exceção no Catalogo, retornando que não existia nenhum catálogo
        waitLoadPage();
        try {
            return new ModalComponenteMap().getModal().isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
