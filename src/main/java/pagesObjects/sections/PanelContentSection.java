package pagesObjects.sections;

import map.ComponenteMap;
import map.PaginacaoMap;
import map.PanelContentMap;
import map.ProvedorMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pagesObjects.ComponentePage;
import pagesObjects.ModalComponentePage;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

import java.util.List;

import static org.junit.Assert.fail;
import static support.Utils.*;
import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.CARR_PAG;

public class PanelContentSection {
    private final PanelContentMap pCM;
    private final PaginacaoSection pS;
    private final PaginacaoMap pM;
    private String nomeItemSelecionado;
    private int indexADM;

    public PanelContentSection() {
        this.pCM = new PanelContentMap();
        this.pS = new PaginacaoSection();
        this.pM = new PaginacaoMap();
    }

    public String getNomeItemSelecionado() {
        return nomeItemSelecionado;
    }

    public int getIndexADM() {
        return indexADM;
    }

    public boolean existeOpcao(boolean esperado, boolean needBeADM, String opcao) {
        do {
            ProvedorMap prM = new ProvedorMap();
            printLog("Procurando por '" + opcao + "'. Testando a página " + pM.getPaginaAtual().getText() + ".", INFO);
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                getNomeItemSelecionado(prM, nItem);
                if (checkListaOpcoes(opcao, nItem)) continue;
                waitLoadPage(CARR_PAG);
                ModalComponentePage mCP = new ModalComponentePage();
                if (!esperado && isGetAlertDisplayed()) {
                    printLog("Projeto sem " + opcao + "encontrado.", INFO);
                    return false;
                } else if (esperado && mCP.isModalDisplayed()) {
                    printLog("Projeto com " + opcao + " encontrado.", INFO);
                    if (needBeADM) {
                        if (!mCP.isUserLoggedADM()) {
                            new ComponentePage().clickBtnFechar(true, "modal");
                            continue;
                        }
                        isUnicoMembro(mCP);
                        this.indexADM = mCP.getIndexADM();
                    }
                    return true;
                }
            }
            pS.avancarPagina(opcao);
        } while (true);
    }

    private void isUnicoMembro(ModalComponentePage mCP) {
        // Testando com um usuario que não seja o do Bruno, ele não consegue excluir o do Bruno. Por isso precisa ser > 2, o usuario do bruno e o logado.
        if (mCP.getListElementosModal().size() <= 2) {
            new ModalComponentePage().acessarForm();
            new ModalAdicionarMembroPage().addMembro();
        }
    }

    private void getNomeItemSelecionado(ProvedorMap prM, WebElement nItem) {
        this.nomeItemSelecionado = prM.getListNomes().get(prM.getListBtnExibir().indexOf(nItem)).getText();
    }

    private boolean checkListaOpcoes(String opcao, WebElement nItem) {
        if (isListaOpcoesDisplayed())
            return acessarSubMenu(nItem, opcao);
        return false;
    }

    private boolean isGetAlertDisplayed() {
        try {
            if (new ComponenteMap().getAlertInfo().isDisplayed()) {
                printLog("O alerta esta sendo mostrado.", INFO);
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            new ComponentePage().clickBtnFechar(true, "modal");
            return false;
        }
    }

    private void avancarItem(WebElement nItem, List<WebElement> listBtnExibir) {
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

    private boolean clicarBotaoOpcao(String opcao) {
        for (WebElement webElement : pCM.getListaOpcoesSubmenu()) {
            if (webElement.getText().intern().equals(opcao)) {
                if (checkBtnDisabled(webElement, "class")) return false;
                rolarPaginaAteElemento(webElement);
                webElement.click();
                return true;
            }
        }
        new Utils().capturaTela();
        fail("A opção '" + opcao + "' não existe no submenu. O teste não pode prosseguir.");
        return false;
    }

    private boolean isListaOpcoesDisplayed() {
        try {
            return pCM.getListaOpcoes().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTxtNenhumResultado(String local) {
        switch (local) {
            case "componente":
                return pCM.getTxtNenhumResultado().getText();
            case "modal":
                return pCM.getTxtNenhumResultadoModal().getText();
            default:
                throw new IllegalStateException("Local citado não existe: " + local);
        }
    }
}