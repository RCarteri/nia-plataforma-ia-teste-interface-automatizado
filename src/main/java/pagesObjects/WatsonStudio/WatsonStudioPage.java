package pagesObjects.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pagesObjects.IBMCloudPage;
import pagesObjects.ModalComponentePage;
import pagesObjects.PaginacaoSection;
import utils.Utils;

import java.util.List;

import static utils.Razoes.CARR_ELEM;
import static utils.Utils.*;

public class WatsonStudioPage extends Pagina {
    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento spanMembro;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(4)")
    private Elemento spanNotebook;

    @MapearElementoWeb(css = ".p-dropdown-clearable .pi-chevron-down")
    private Elemento dropDownSigla;

    @MapearElementoWeb(css = ".pi-refresh")
    private ElementoBotao btnAtualizar;

    private WebElement getModeloBotao() {
        return getElements("a.ng-star-inserted").get(0);
    }

    public void selecionarSigla(String sigla) throws ElementoNaoLocalizadoException {
        dropDownSigla.clicar();
        List<WebElement> listSigla = getDriver().findElements(By.cssSelector("p-dropdownitem span"));
        for (WebElement webElement : listSigla) {
            if (webElement.getText().intern().equals(sigla.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public boolean clicarBotaoOpcao(String opcao) {
        try {
            switch (opcao) {
                case "Notebooks":
                    spanNotebook.clicar();
                    break;
                case "Membros":
                    spanMembro.clicar();
                    break;
                case "Modelos":
                    if (getModeloBotao().getAttribute("class").contains("disabled")) return false;
                    getModeloBotao().click();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return true;
    }

    public void atualizarProjetos() {
        try {
            btnAtualizar.clicar();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public boolean existeOpcao(boolean esperado, String opcao) {
        try {
            PaginacaoSection pS = new PaginacaoSection();
            for (WebElement nPagina : pS.listBtnNPaginacao) {
                IBMCloudPage iCP = new IBMCloudPage();
                for (WebElement nItem : iCP.listBtnExibir) {
                    if (!avancarItem(nItem, iCP.listBtnExibir, opcao)) continue;
                    if (!esperado && iCP.getAlert().isDisplayed()) {
                        System.out.println("Encontrado projeto sem " + opcao + ".");
                        return false;
                    } else if (!esperado && new ModalComponentePage().btnFechar.elementoExiste()) {
                        System.out.println("Fechando modal");
                        new ModalComponentePage().btnFechar.clicar();
                    } else if (esperado && new ModalComponentePage().getCountLinhas() >= 1) {
                        System.out.println("Projeto com " + opcao + " encontrado.");
                        return true;
                    }
                }
                avancarPagina(nPagina);
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return false;
    }

    private void avancarPagina(WebElement nPagina) {
        System.out.println("Avançando para a página " + nPagina.getText());
        Utils.rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }

    private boolean avancarItem(WebElement nItem, List<WebElement> listBtnExibir, String opcao) {
        System.out.println("Testando o " + (listBtnExibir.indexOf(nItem) + 1) + "º projeto da lista.");
        rolarPaginaAteElemento(nItem);
        nItem.click();
        if (!clicarBotaoOpcao(opcao)) {
            nItem.click();
            return false;
        }
        new Utils().esperar(tempoQTeste(CARR_ELEM.getDelay()), CARR_ELEM.getRazao());
        return true;
    }
}
