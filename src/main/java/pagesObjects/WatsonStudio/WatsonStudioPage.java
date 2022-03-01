package pagesObjects.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Utils.getDriver;

public class WatsonStudioPage extends Pagina {
    @MapearElementoWeb(css = ".p-button-sm")
    private ElementoBotao btnOpcoes;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento spanMembro;

    @MapearElementoWeb(css = ".p-dropdown-clearable .pi-chevron-down")
    private Elemento dropDownSigla;


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
        try {
            if ("Membros".equals(opcao)) {
                spanMembro.clicar();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }
}
