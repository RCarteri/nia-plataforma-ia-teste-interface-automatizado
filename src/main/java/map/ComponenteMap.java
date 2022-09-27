package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;
import static support.Utils.waitLoadPage;
import static support.enums.SelectorsDelays.CARR_PAG;

public class ComponenteMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-dropdown-trigger span")
    private WebElement dropComponente;

    @FindBy(cssSelector = ".p-toast-message-success")
    private WebElement alertSuccess;

    public WebElement getTituloComponente() {
        waitLoadPage(CARR_PAG);
        try {
            return getElement(".main-container h1");
        } catch (Exception e) {
            //Legado
            return getElement("#p-panel-1-titlebar");
        }
    }

    public WebElement getDropDownComponente() {
        if (dropComponente == null)
            dropComponente = setElement("dropComponente");
        return dropComponente;
    }

    public WebElement getAlertInfo() {
        waitLoadPage(CARR_PAG);
        //Se usar o FindBy trava no CT009 Inexistência do componente
        return getElement(".p-toast-message-info .p-toast-detail");
    }

    public WebElement getAlertSuccess() {
        waitLoadPage(CARR_PAG);
        if (alertSuccess == null)
            alertSuccess = setElement("alertSuccess");
        return alertSuccess;
    }

    public List<WebElement> getListComponente() {
        return getElements(".p-dropdown-items-wrapper span");
    }

    public List<WebElement> getListBtnFecharAlerta() {
        return getElements(".p-toast .pi-times");
    }

    public WebElement getBtnFecharModal() {
        return getElement(".p-dialog-header .pi-times");
    }

    public WebElement getForm() {
        return getElement(".p-dialog-content form");
    }

    public List<WebElement> getListCards() {
        return getElements("bb-card");
    }
}
