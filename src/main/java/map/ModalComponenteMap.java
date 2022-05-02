package map;

import org.openqa.selenium.WebElement;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;
import static support.Utils.waitElement;

public class ModalComponenteMap extends BasePageObjects {
    public WebElement getTituloModal() {
        return getElement(".p-dialog-title");
    }

    public WebElement getModal() {
        return waitElement("div.p-dialog-header");
    }

    public List<WebElement> getListInfoNomeID() {
        return getElements(".ng-trigger h4");
    }

    public List<WebElement> getListaElementosModal() {
        return getElements(".p-dialog-content td");
    }

    public List<WebElement> getListCabecalhoNomeID() {
        return getElements("div[class*='p-ai-center ng-tns'] h4");
    }
}