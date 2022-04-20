package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.List;

import static support.Razoes.CARR_ELEM;
import static support.Utils.rolarPaginaAteElemento;

public class PanelContentSection extends Pagina {
    private final PanelContentMap pCM = new PanelContentMap();
    private String nomeItemSelecionado;

    public String getNomeItemSelecionado() {
        return nomeItemSelecionado;
    }

    public boolean existeOpcao(boolean esperado, String opcao) {
        PaginacaoMap pM = new PaginacaoMap();
        for (WebElement nPagina : pM.getListBtnNPaginacao()) {
            ProvedorMap prM = new ProvedorMap();
            new PaginacaoSection().avancarPagina(nPagina);
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                if (checkListaOpcoes(opcao, nItem)) continue;
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
        ModalComponenteMap mCM = new ModalComponenteMap();
        try {
            new ComponenteMap().getAlert().isDisplayed();
        } catch (Exception e) {
            if (mCM.getBtnFechar().elementoExiste()) {
                System.out.println("Fechando modal");
                try {
                    mCM.getBtnFechar().clicar();
                } catch (ElementoNaoLocalizadoException ex) {
                    Utils.logError(ex);
                }
                return false;
            } else e.printStackTrace();
        }
        return true;
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
        new Utils().esperarQTeste(CARR_ELEM.getDelay(), CARR_ELEM.getRazao());
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
            pCM.getListaOpcoes().isDisplayed();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getTxtNenhumResultado(@NotNull String local) {
        try {
            switch (local) {
                case "componente":
                    return pCM.getTxtNenhumResultado().recuperarTexto();
                case "modal":
                    return pCM.getTxtNenhumResultadoModal().recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }
}