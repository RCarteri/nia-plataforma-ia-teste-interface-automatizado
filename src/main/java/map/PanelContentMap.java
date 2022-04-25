package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class PanelContentMap extends BasePageObjects {
    @FindBy(cssSelector = "nia-platia-table td")
    private WebElement txtNenhumResultado;

    @FindBy(cssSelector = "nia-membros-table td")
    private WebElement txtNenhumResultadoModal;

    public WebElement getTxtNenhumResultadoModal() {
        if (txtNenhumResultadoModal == null)
            txtNenhumResultadoModal = setElement("txtNenhumResultadoModal");
        return txtNenhumResultadoModal;
    }

    public WebElement getTxtNenhumResultado() {
        if (txtNenhumResultado == null)
            txtNenhumResultado = setElement("txtNenhumResultado");
        return txtNenhumResultado;
    }

    public WebElement getListaOpcoes() {
        return getElement("div.p-menu");
    }

    public List<WebElement> getListaOpcoesSubmenu(){
        return  getElements("li.ng-star-inserted:nth-child(n+2) a");
    }
}
