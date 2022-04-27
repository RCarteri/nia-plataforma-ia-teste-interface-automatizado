package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class ModalComponenteMap extends BasePageObjects {
    @FindBy(cssSelector = ".pi-times")
    private WebElement btnFechar;

    public WebElement getTituloModal() {
        return getElement(".p-dialog-title");
    }

    public WebElement getModal() {
        return getElement("div.p-ai-center");
    }

    public WebElement getBtnFechar() {
        if (btnFechar == null)
            btnFechar = setElement("btnFechar");
        return btnFechar;
    }

    public List<WebElement> getListInfoNomeID() {
        return getElements(".ng-trigger h4");
    }

    public List<WebElement> getListaElementosModal() {
        return getElements(".p-dialog-content td");
    }

    public List<WebElement> getListCabecalhoNomeID() {
        return getElements("div[class*='p-ai-center ng-tns-c47'] span");
    }
}
