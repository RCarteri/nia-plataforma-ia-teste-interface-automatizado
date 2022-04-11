package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.List;

import static support.Razoes.CARR_ELEM;
import static support.Utils.logError;
import static support.Utils.rolarPaginaAteElemento;

public class PanelContentSection {
    private final PanelContentMap pCM = new PanelContentMap();

    public boolean existeOpcao(boolean esperado, String opcao) {
        final PaginacaoMap pM = new PaginacaoMap();
        final ModalComponentePage mCP = new ModalComponentePage();
        for (WebElement nPagina : pM.getListBtnNPaginacao()) {
            final ProvedorMap prM = new ProvedorMap();
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                if (isListaOpcoesDisplayed())
                    if (acessarSubMenu(nItem, opcao))
                        continue;
                if (!esperado && isGetAlertDisplayed()) {
                    System.out.println("Encontrado projeto sem " + opcao + ".");
                    return false;
                } else if (esperado && mCP.isModalDisplayed()) {
                    System.out.println("Projeto com " + opcao + " encontrado.");
                    return true;
                }
            }
            new PaginacaoSection().avancarPagina(nPagina);
        }
        return false;
    }

    private boolean isListaOpcoesDisplayed(){
        try{
            return pCM.getListaOpcoes().isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    private boolean isGetAlertDisplayed() {
        final ModalComponenteMap mCM = new ModalComponenteMap();
        try {
            new ComponenteMap().getAlert().isDisplayed();
        } catch (Exception e) {
            if (mCM.getBtnFechar().elementoExiste()) {
                System.out.println("Fechando modal");
                try {
                    mCM.getBtnFechar().clicar();
                } catch (ElementoNaoLocalizadoException ex) {
                    logError(ex);
                }
                return false;
            } else e.printStackTrace();
        }
        return true;
    }

    private void avancarItem(WebElement nItem, @NotNull List<WebElement> listBtnExibir) {
        if(listBtnExibir.indexOf(nItem) > 0) System.out.println("Elemento procurado não encontrado.");
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

    public String getTxtNenhumResultado(@NotNull String local) {
        try {
            switch (local) {
                case "componente":
                    return pCM.getTxtNenhumResultado().recuperarTexto();
                case "modal":
                    return pCM.getTxtNenhumResultadoModal().recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
        return null;
    }
}