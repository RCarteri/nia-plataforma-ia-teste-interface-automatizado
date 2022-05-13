package pagesObjects;

import map.ModalComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;
import static support.enums.User.getUser;

public class ModalComponentePage extends ModalComponenteMap {
    private int indexADM;

    public int getCountLinhas() {
        return getListaElementosModal().size();
    }

    public List<String> getListaInfoNomeID() {
        List<String> listaElementosVazios = new ArrayList<>();
        int index = 0;
        for (WebElement info : getListInfoNomeID()) {
            if (info.getText().length() == 0)
                listaElementosVazios.add(getListCabecalhoNomeID().get(index).getText());
            index++;
        }
        return listaElementosVazios;
    }

    public boolean isModalDisplayed() {
        try {
            return new ModalComponenteMap().getModal().isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            new ComponentePage().clickBtnFechar(true, "alerta");
            return false;
        }
    }

    public boolean isNomeIgual(String nomeItemSelecionado) {
        return getNomeElemento().equals(nomeItemSelecionado);
    }

    public String getNomeElemento() {
        return getListCabecalhoNomeID().get(0).getText();
    }

    public boolean isUserLoggedADM() {
        if (haMaisDeUmMembro())
            for (int i = 0; i < getListNome().size(); i++)
                return isUserLoggedADM(i);
        return false;
    }

    private boolean haMaisDeUmMembro() {
        return getListNome().size() > 1;
    }

    private boolean isUserLoggedADM(int i) {
        if (isUserLogged(i)) {
            return isAdmin(i);
        }
        return false;
    }

    public int getIndexADM() {
        return indexADM;
    }

    private boolean isUserLogged(int i) {
        return getListNome().get(i).getText().equals(getUser());
    }

    private boolean isAdmin(int i) {
        if (getPapel(i).getText().equals("Administrador")) {
            printLog("Encontrado projeto onde o " + getUser() + " é administrador.", INFO);
            this.indexADM = i;
            return true;
        }
        return false;
    }

    public void editarPapel(int indexADM) {
        int indexRandom = getIndexRandom(indexADM);
        getBtnEditar(indexRandom).click();
        String papelAtual = getPapel(indexRandom).getText();
        getPapel(indexRandom).click();
        selecionarPapelDiferente(papelAtual);
        getBtnSalvar().click();
    }

    private void selecionarPapelDiferente(String papelAtual) {
        for (WebElement wEPapel : getListPapel()) {
            if (!wEPapel.getText().equals(papelAtual)) {
                wEPapel.click();
                break;
            }
        }
    }

    private int getIndexRandom(int indexADM) {
        Random rnd = ThreadLocalRandom.current();
        int indexRandom = rnd.nextInt(getListNome().size() - 1) + 1;
        return (indexRandom == indexADM) ? getIndexRandom(indexADM) : indexRandom;
    }
}
