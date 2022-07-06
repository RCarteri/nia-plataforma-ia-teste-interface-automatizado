package pagesObjects;

import map.ModalComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static support.Utils.*;
import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.CARR_PAG;
import static support.enums.User.getUser;

public class ModalComponentePage extends ModalComponenteMap {
    private int indexADM;

    public int getCountLinhas() {
        return getListElementosModal().size();
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
            waitLoadPage(CARR_PAG);
            try {
                return new ModalComponenteMap().getComponenteTransferirS3().isDisplayed();
            } catch (NoSuchElementException | TimeoutException ex) {
                new ComponentePage().clickBtnFechar(true, "alerta");
                return false;
            }
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
                if (isUserLoggedADM(i)) return true;
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
        getListPapel().stream()
                .filter(wEPapel -> !wEPapel.getText().equals(papelAtual))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    private int getIndexRandom(int indexADM) {
        int indexRandom = getRandom(getListNome().size());
        return (indexRandom == indexADM || getListNome().get(indexRandom).getText().equals("Bruno")) ? getIndexRandom(indexADM) : indexRandom;
    }

    public void excluirMembro(int indexADM) {
        getBtnExcluir(getIndexRandom(indexADM)).click();
        new ModalComponenteMap().getBtnConfirmarExclusao().click();
    }

    public void acessarForm() {
        assertFalse("O usuário " + getUser() + " não tem permissão para adicionar membro. Não foi possível realizar este teste.",
                checkBtnDisabled(getBtnAdicionarMembro(), "btn"));
        getBtnAdicionarMembro().click();
    }
}
