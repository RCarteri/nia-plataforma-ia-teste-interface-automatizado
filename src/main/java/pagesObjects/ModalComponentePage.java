package pagesObjects;

import map.ModalComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ModalComponentePage {
    private final ModalComponenteMap mCM;

    public ModalComponentePage() {
        this.mCM = new ModalComponenteMap();
    }

    public int getCountLinhas() {
        return mCM.getListaElementosModal().size();
    }

    public List<String> getListaInfoNomeID() {
        List<String> listaElementosVazios = new ArrayList<>();
        int index = 0;
        for (WebElement info : mCM.getListInfoNomeID()) {
            if (info.getText().length() == 0)
                listaElementosVazios.add(mCM.getListCabecalhoNomeID().get(index).getText());
            index++;
        }
        return listaElementosVazios;
    }

    protected boolean isModalDisplayed() {
        try {
           return new ModalComponenteMap().getModal().isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            new ComponentePage().clickBtnFechar(true,"alerta");
            return false;
        }
    }

    public boolean isNomeIgual(String nomeItemSelecionado) {
        return getNomeElemento().equals(nomeItemSelecionado);
    }

    public String getNomeElemento() {
        return mCM.getListCabecalhoNomeID().get(0).getText();
    }
}
