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
import utils.Utils;

import java.util.List;

import static utils.Utils.getDriver;
import static utils.Utils.getElement;

public class WatsonStudioPage extends Pagina {
    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento spanMembro;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(4)")
    private Elemento spanNotebook;

    @MapearElementoWeb(css = ".p-dropdown-clearable .pi-chevron-down")
    private Elemento dropDownSigla;

    @MapearElementoWeb(css = ".pi-refresh")
    private ElementoBotao btnAtualizar;

    private List<WebElement> listbtnOpcoes = null;
    private List<WebElement> listbtnPaginacao = null;

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

    public void clicarBotaoOpcao(String opcao) {
        this.listbtnOpcoes = getElement(".pi-ellipsis-v");
        this.listbtnPaginacao = getElement("button.p-paginator-page");
        try {
            switch (opcao) {
                case "Notebooks":
                    spanNotebook.clicar();
                    break;
                case "Membros":
                    spanMembro.clicar();
                    break;
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

    public boolean existeNotebook() {
        ModalComponentePage mCP = new ModalComponentePage();
        for (WebElement paginacao : listbtnPaginacao) {
            for (WebElement opcao : listbtnOpcoes) {
                if (new IBMCloudPage().alertMensagem.elementoExiste()) {
                    return true;
                }
                System.out.println("\n    INFO - Testando próximo item da listagem");
                try {
                    if (mCP.btnFechar.elementoExiste()) {
                        mCP.btnFechar.clicar();
                    }
                    opcao.click();
                } catch (ElementoNaoLocalizadoException e) {
                    Utils.logError(e);
                }
                clicarBotaoOpcao("Notebooks");
            }
            int index = listbtnPaginacao.indexOf(paginacao);
            listbtnPaginacao.get(index + 1).click();
            System.out.println("\n    INFO - Avançando paginação");
            this.listbtnOpcoes = getElement(".pi-ellipsis-v");
        }
        return false;
    }
}
