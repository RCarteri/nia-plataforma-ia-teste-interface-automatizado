package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Utils.getElements;
import static utils.Utils.waitElement;

public class ComponentePage extends Pagina {
    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".p-dropdown-trigger span")
    private Elemento dropDownComponente;

    public WebElement getAlert() {
        return waitElement("div .p-toast-detail");
    }

    private List<WebElement> getListComponente() {
        return getElements(".p-dropdown-items-wrapper span");
    }

    public void acessarComponente(String componente) throws ElementoNaoLocalizadoException {
        dropDownComponente.clicar();
        List<WebElement> listComponente = getListComponente();
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

}
