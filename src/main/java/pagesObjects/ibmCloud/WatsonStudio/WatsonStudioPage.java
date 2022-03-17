package pagesObjects.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import pagesObjects.IBMCloudPage;
import pagesObjects.ModalComponentePage;
import pagesObjects.PaginacaoSection;
import pagesObjects.ProvedorPage;
import utils.Utils;

import java.util.List;

import static utils.Razoes.CARR_ELEM;
import static utils.Utils.getElements;
import static utils.Utils.rolarPaginaAteElemento;

public class WatsonStudioPage extends Pagina {
    @MapearElementoWeb(css = ".p-dropdown-clearable .pi-chevron-down")
    private Elemento dropDownSigla;

    @MapearElementoWeb(css = ".pi-refresh")
    private ElementoBotao btnAtualizar;

    private List<WebElement> getListSigla() {
        return getElements("p-dropdownitem span");
    }

    public void selecionarSigla(String sigla) {
        try {
            dropDownSigla.clicar();
            for (WebElement webElement : getListSigla()) {
                if (webElement.getText().intern().equals(sigla.intern())) {
                    webElement.click();
                    break;
                }
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
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
                ProvedorPage pP = new ProvedorPage();
                for (WebElement nItem : pP.listBtnExibir) {
                    if (!avancarItem(nItem, pP.listBtnExibir, opcao)) continue;
                    new Utils().esperarQTeste(CARR_ELEM.getDelay(), CARR_ELEM.getRazao());
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
        boolean continuar = new ProvedorPage().clicarBotaoOpcao(opcao);
        if (!continuar) {
            nItem.click();
            return false;
        }
        return true;
    }
}
