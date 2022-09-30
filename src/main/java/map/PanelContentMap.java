package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class PanelContentMap extends BasePageObjects {
    public WebElement getTxtNenhumResultadoModal() {
        return getElement(".p-dialog-content td:only-of-type");
    }

    public WebElement getTxtNenhumResultado() {
       return getElement("nia-platia-table td:only-of-type");
    }

    public WebElement getListaOpcoes() {
        return getElement("div.p-menu");
    }

    public List<WebElement> getListaOpcoesSubmenu(){
        return  getElements("li.ng-star-inserted:nth-child(n+2) a");
    }
}
