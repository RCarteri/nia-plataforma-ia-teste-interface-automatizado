package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;
import static support.Utils.waitElement;

public class ComponenteMap extends BasePageObjects {
    @FindBy(cssSelector = "#p-panel-1-titlebar")
    private WebElement tituloComponente;

    @FindBy(cssSelector = ".p-dropdown-trigger span")
    private WebElement dropComponente;

    @FindBy(cssSelector = ".p-toast-message-success .p-toast-detail")
    private WebElement alertSuccess;

    public WebElement getTituloComponente() {
        if (tituloComponente == null)
            tituloComponente = setElement("tituloComponente");
        return tituloComponente;
    }

    public WebElement getDropDownComponente() {
        if (dropComponente == null)
            dropComponente = setElement("dropComponente");
        return dropComponente;
    }

    public WebElement getAlertInfo() {
        return waitElement(".p-toast-message-info .p-toast-detail");
    }

    public WebElement getAlertSuccess() {
        if (alertSuccess == null)
            alertSuccess = setElement("alertSuccess");
        return alertSuccess;
    }

    public List<WebElement> getListComponente() {
        return getElements(".p-dropdown-items-wrapper span");
    }

    public List<WebElement> getListBtnFecharAlerta() {
        return getElements("p-toastitem button");
    }
}
