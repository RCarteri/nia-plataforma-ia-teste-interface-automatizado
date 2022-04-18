package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElement;
import static support.Utils.getElements;

public class PanelContentMap extends Pagina {
    @MapearElementoWeb(css = "nia-platia-table td")
    private ElementoTexto txtNenhumResultado;

    @MapearElementoWeb(css = "nia-membros-table td")
    private ElementoTexto txtNenhumResultadoModal;

    public WebElement getListaOpcoes() {
        return getElement("div.p-menu");
    }

    public List<WebElement> getListaOpcoesSubmenu(){
        return  getElements("li.ng-star-inserted:nth-child(n+2) a");
    }

    public ElementoTexto getTxtNenhumResultado() {
        return txtNenhumResultado;
    }

    public ElementoTexto getTxtNenhumResultadoModal() {
        return txtNenhumResultadoModal;
    }
}
