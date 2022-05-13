package pagesObjects.sections;

import map.ComponenteMap;
import map.PaginacaoMap;
import map.PanelContentMap;
import map.ProvedorMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pagesObjects.ComponentePage;
import pagesObjects.ModalComponentePage;

import java.util.List;

import static support.Utils.*;
import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.CARR_PAG;

public class PanelContentSection {
    private final PanelContentMap pCM;
    private String nomeItemSelecionado;
    private int indexADM;

    public PanelContentSection() {
        this.pCM = new PanelContentMap();
    }

    public String getNomeItemSelecionado() {
        return nomeItemSelecionado;
    }

    public int getIndexADM() {
        return indexADM;
    }

    public boolean existeOpcao(boolean esperado, boolean needBeADM, String opcao) {
        for (WebElement nPagina : new PaginacaoMap().getListBtnNPaginacao()) {
            ProvedorMap prM = new ProvedorMap();
            new PaginacaoSection().avancarPagina(nPagina);
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                if (checkListaOpcoes(opcao, nItem)) continue;
                waitLoadPage(CARR_PAG);
                ModalComponentePage mCP = new ModalComponentePage();
                if (!esperado && isGetAlertDisplayed()) {
                    printLog("Projeto sem " + opcao + "encontrado.", INFO);
                    return false;
                } else if (esperado && mCP.isModalDisplayed()) {
                    printLog("Projeto com " + opcao + " encontrado.", INFO);
                    this.nomeItemSelecionado = prM.getListNomes().get(prM.getListBtnExibir().indexOf(nItem)).getText();
                    if (needBeADM){
                        if (!mCP.isUserLoggedADM()) {
                            new ComponentePage().clickBtnFechar(true, "modal");
                            continue;
                        }
                        this.indexADM = mCP.getIndexADM();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkListaOpcoes(String opcao, WebElement nItem) {
        if (isListaOpcoesDisplayed())
            return acessarSubMenu(nItem, opcao);
        return false;
    }

    private boolean isGetAlertDisplayed() {
        try {
            printLog(new ComponenteMap().getAlertInfo().isDisplayed() + "alerta esta sendo mostrado.", INFO);
            return new ComponenteMap().getAlertInfo().isDisplayed();
        } catch (NoSuchElementException e) {
            new ComponentePage().clickBtnFechar(true, "modal");
            return false;
        }
    }

    private void avancarItem(WebElement nItem, @NotNull List<WebElement> listBtnExibir) {
        if (listBtnExibir.indexOf(nItem) > 0) printLog("Elemento procurado não encontrado.", INFO);
        printLog("Testando o " + (listBtnExibir.indexOf(nItem) + 1) + "º projeto da lista.", INFO);
        rolarPaginaAteElemento(nItem);
        nItem.click();
    }

    private boolean acessarSubMenu(WebElement nItem, String opcao) {
        boolean btnHabilitado = clicarBotaoOpcao(opcao);
        if (!btnHabilitado) {
            nItem.click();
            return true;
        }
        return false;
    }

    private boolean clicarBotaoOpcao(@NotNull String opcao) {
        for (WebElement webElement : pCM.getListaOpcoesSubmenu()) {
            if (webElement.getText().intern().equals(opcao)) {
                if (webElement.getAttribute("class").contains("disabled")) return false;
                rolarPaginaAteElemento(webElement);
                webElement.click();
                break;
            }
        }
        return true;
    }

    private boolean isListaOpcoesDisplayed() {
        try {
            return pCM.getListaOpcoes().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTxtNenhumResultado(@NotNull String local) {
        switch (local) {
            case "componente":
                return pCM.getTxtNenhumResultado().getText();
            case "modal":
                return pCM.getTxtNenhumResultadoModal().getText();
        }
        return null;
    }
}