package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import map.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.List;

import static support.Utils.rolarPaginaAteElemento;
import static support.enums.TimesAndReasons.CARR_ELEM;

public class PanelContentSection extends Pagina {
    private final PanelContentMap pCM = new PanelContentMap();

    public boolean existeOpcao(boolean esperado, String opcao) {
        PaginacaoMap pM = new PaginacaoMap();
        for (WebElement nPagina : pM.getListBtnNPaginacao()) {
            ProvedorMap prM = new ProvedorMap();
            new PaginacaoSection().avancarPagina(nPagina);
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                if (isListaOpcoesDisplayed())
                    if (acessarSubMenu(nItem, opcao)) continue;
                if (!esperado && isGetAlertDisplayed()) {
                    System.out.println("Encontrado projeto sem " + opcao + ".");
                    return false;
                } else if (esperado && new ModalComponentePage().isModalDisplayed()) {
                    System.out.println("Projeto com " + opcao + " encontrado.");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isGetAlertDisplayed() {
        ModalComponenteMap mCM = new ModalComponenteMap();
        try {
            new ComponenteMap().getAlertInfo().isDisplayed();
        } catch (Exception e) {
            if (mCM.getBtnFechar().isDisplayed()) {
                System.out.println("Fechando modal");
                mCM.getBtnFechar().click();
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
        new Utils().esperarQTeste(CARR_ELEM);
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
        switch (local) {
            case "componente":
                return pCM.getTxtNenhumResultado().getText();
            case "modal":
                return pCM.getTxtNenhumResultadoModal().getText();
        }
        return null;
    }
}