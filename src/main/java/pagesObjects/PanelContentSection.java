package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.Arrays;
import java.util.List;

import static support.Razoes.CARR_ELEM;
import static support.Utils.rolarPaginaAteElemento;

public class PanelContentSection extends Pagina {
    private final PanelContentMap pCM = new PanelContentMap();
        private final ModalComponentePage mCP = new ModalComponentePage();
    private final PaginacaoSection pS = new PaginacaoSection();

    public boolean existeOpcao(boolean esperado, String opcao) {
        List<String> opcoes = Arrays.asList("Modelos", "Notebooks", "Membros", "Detalhes", "Testar Modelo");
        PaginacaoMap pM = new PaginacaoMap();
        for (WebElement nPagina : pM.getListBtnNPaginacao()) {
            ProvedorMap prM = new ProvedorMap();
            for (WebElement nItem : prM.getListBtnExibir()) {
                avancarItem(nItem, prM.getListBtnExibir());
                if (opcoes.contains(opcao)) if (acessarSubMenu(nItem, opcao)) continue;
                if (!esperado && isGetAlertDisplayed()) {
                    System.out.println("Encontrado projeto sem " + opcao + ".");
                    return false;
                } else if (esperado && mCP.isModalDisplayed()) {
                    System.out.println("Projeto com " + opcao + " encontrado.");
                    return true;
                }
            }
            pS.avancarPagina(nPagina);
        }
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
        try {
            switch (opcao) {
                case "Notebooks":
                    pCM.getTerceiraOpcao().clicar();
                    break;
                case "Membros":
                case "Testar Modelo":
                    pCM.getSegundaOpcao().clicar();
                    break;
                case "Modelos":
                case "Detalhes":
                    if (pCM.getPrimeiraOpcao().getAttribute("class").contains("disabled")) return false;
                    pCM.getPrimeiraOpcao().click();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
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