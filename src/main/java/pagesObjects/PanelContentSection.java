package pagesObjects;

import map.ComponenteMap;
import map.PaginacaoMap;
import map.PanelContentMap;
import map.ProvedorMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.rolarPaginaAteElemento;
import static support.Utils.waitLoadPage;

public class PanelContentSection {
    private final PanelContentMap pCM;
    private String nomeItemSelecionado;

    public PanelContentSection() {
        this.pCM = new PanelContentMap();
    }

    public String getNomeItemSelecionado() {
        return nomeItemSelecionado;
    }

    public boolean existeOpcao(boolean esperado, String opcao) {
        for (WebElement nPagina : new PaginacaoMap().getListBtnNPaginacao()) {
            ProvedorMap prM = new ProvedorMap();
            new PaginacaoSection().avancarPagina(nPagina);
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                if (checkListaOpcoes(opcao, nItem)) continue;
                waitLoadPage();
                if (!esperado && isGetAlertDisplayed()) {
                    System.out.println("Encontrado projeto sem " + opcao + ".");
                    return false;
                } else if (esperado && new ModalComponentePage().isModalDisplayed()) {
                    System.out.println("Projeto com " + opcao + " encontrado.");
                    this.nomeItemSelecionado = prM.getListNomes().get(prM.getListBtnExibir().indexOf(nItem)).getText();
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
            System.out.println(new ComponenteMap().getAlertInfo().isDisplayed() + "alerta esta sendo mostrado");
            return new ComponenteMap().getAlertInfo().isDisplayed();
        } catch (NoSuchElementException e) {
            new ComponentePage().clickBtnFechar(true,"modal");
            return false;
        }
    }

    private void avancarItem(WebElement nItem, @NotNull List<WebElement> listBtnExibir) {
        if (listBtnExibir.indexOf(nItem) > 0) System.out.println("Elemento procurado não encontrado.");
        System.out.println("Testando o " + (listBtnExibir.indexOf(nItem) + 1) + "º projeto da lista.");
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