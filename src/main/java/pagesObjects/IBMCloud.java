package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Utils.getDriver;
import static utils.Utils.rolarPaginaAteElemento;

public class IBMCloud extends Pagina {

    private final List<WebElement> listBtnExibir = getDriver().findElements(By.cssSelector(".p-button-secondary"));

    @MapearElementoWeb(css = "#p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(xPath = "//*[@id='p-panel-0-content']/div/p-dropdown/div/span")
    private Elemento dropDownComponente;

    @MapearElementoWeb(xPath = "//span[contains(text(), 'Watson Studio')]")
    private ElementoTexto spanWatsonStudio;

    @MapearElementoWeb(xPath = "//span[contains(text(), 'Watson Assistant')]")
    private ElementoTexto spanWatsonAssistant;

    @MapearElementoWeb(xPath = "//span[contains(text(), 'Watson Knowledge Catalog')]")
    private ElementoTexto spanWatsonKnowledgeCatalog;

    @MapearElementoWeb(xPath = "//span[contains(text(), 'Cloud Object Storage')]")
    private ElementoTexto spanCloudObjectStorage;

    @MapearElementoWeb(xPath = "//span[contains(text(), 'Modelos')]")
    private ElementoTexto spanModelos;

    @MapearElementoWeb(xPath = "//span[contains(text(), 'Grupos de Acesso')]")
    private ElementoTexto spanGruposDeAcesso;

    public void clicarBotaoLista(int localizacao) {
        localizacao--;
        rolarPaginaAteElemento(listBtnExibir.get(localizacao));
        listBtnExibir.get(localizacao).click();
    }

    public void acessarComponente(String componente) throws ElementoNaoLocalizadoException {
        ativarDropDown();
        List<WebElement> listComponente = getDriver().findElements(By.cssSelector(".ng-tns-c45-5 li span"));
        for (WebElement webElement : listComponente) {
            if (webElement.getText().intern().equals(componente.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public String getTituloComponente() throws ElementoNaoLocalizadoException {
        return this.divTituloComponente.recuperarTexto();
    }

    private void ativarDropDown() throws ElementoNaoLocalizadoException {
        dropDownComponente.clicar();
    }
}
