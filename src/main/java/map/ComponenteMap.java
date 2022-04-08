package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Utils.getElements;
import static utils.Utils.waitElement;

public class ComponenteMap extends Pagina {
    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".p-dropdown-trigger span")
    private Elemento dropDownComponente;

    public WebElement getAlert() {
        return waitElement("div .p-toast-detail");
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
