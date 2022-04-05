package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

import static utils.Razoes.CARR_ELEM;
import static utils.Utils.getElements;
import static utils.Utils.rolarPaginaAteElemento;

public class PanelContentSection extends Pagina {
    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento segundaOpcao;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(4)")
    private Elemento terceiraOpcao;

    private WebElement getPrimeiraOpcao() {
        return getElements("a.ng-star-inserted").get(0);
    }

    public boolean existeOpcao(boolean esperado, String opcao) {
        List<String> opcoes = Arrays.asList("Modelos", "Notebooks", "Membros", "Detalhes", "Testar Modelo");
        PaginacaoSection pS = new PaginacaoSection();
        for (WebElement nPagina : pS.listBtnNPaginacao) {
            ProvedorPage pP = new ProvedorPage();
            for (WebElement nItem : pP.listBtnExibir) {
                avancarItem(nItem, pP.listBtnExibir);
                if (opcoes.contains(opcao)) if (acessarSubMenu(nItem, opcao)) continue;
                    if (!esperado && isGetAlertDisplayed()) {
                        System.out.println("Encontrado projeto sem " + opcao + ".");
                        return false;
                    } else if (esperado && isModalDisplayed()) {
                        System.out.println("Projeto com " + opcao + " encontrado.");
                        return true;
                    }
            }
            avancarPagina(nPagina);
        }
        return false;
    }

    private boolean isModalDisplayed() {
        return new ModalComponentePage().getTituloModal().elementoExiste();
    }

    private boolean isGetAlertDisplayed() {
        ModalComponentePage mCP = new ModalComponentePage();
        try {
            new IBMCloudPage().getAlert().isDisplayed();
        } catch (Exception e) {
            if (mCP.btnFechar.elementoExiste()) {
                System.out.println("Fechando modal");
                try {
                    mCP.btnFechar.clicar();
                } catch (ElementoNaoLocalizadoException ex) {
                    Utils.logError(ex);
                }
                return false;
            } else e.printStackTrace();
        }
        return true;
    }

    private void avancarPagina(WebElement nPagina) {
        System.out.println("Avançando para a página " + nPagina.getText());
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }

    private void avancarItem(WebElement nItem, List<WebElement> listBtnExibir) {
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

    private boolean clicarBotaoOpcao(String opcao) {
        try {
            switch (opcao) {
                case "Notebooks":
                    terceiraOpcao.clicar();
                    break;
                case "Membros":
                case "Testar Modelo":
                    segundaOpcao.clicar();
                    break;
                case "Modelos":
                case "Detalhes":
                    if (getPrimeiraOpcao().getAttribute("class").contains("disabled")) return false;
                    getPrimeiraOpcao().click();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return true;
    }
}