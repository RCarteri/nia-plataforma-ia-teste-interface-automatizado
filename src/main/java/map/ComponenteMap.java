package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.List;

import static support.Utils.getElements;
import static support.Utils.waitElement;

public class ComponenteMap extends Pagina {
    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".p-dropdown-trigger span")
    private Elemento dropDownComponente;

    public WebElement getAlert() {
        WebElement webElement = null;
        try {
             webElement = waitElement("div .p-toast-detail");
        }catch (TimeoutException e){
            Utils.logError(e);
        }
        return webElement;
    }

    public List<WebElement> getListComponente() {
        return getElements(".p-dropdown-items-wrapper span");
    }

    public ElementoTexto getDivTituloComponente() {
        return divTituloComponente;
    }

    public Elemento getDropDownComponente() {
        return dropDownComponente;
    }
}
