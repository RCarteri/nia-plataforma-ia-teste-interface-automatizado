package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class ComponenteMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-dropdown-trigger span")
    private WebElement dropComponente;

    @FindBy(cssSelector = ".p-toast-message-success")
    private WebElement alertSuccess;

    @FindBy(cssSelector = ".p-toast-message-info .p-toast-detail")
    private WebElement alertInfo;

    public WebElement getTituloComponente() {
        return getElement(".main-container h1");
    }

    public WebElement getDropDownComponente() {
        if (dropComponente == null)
            dropComponente = setElement("dropComponente");
        return dropComponente;
    }

    public WebElement getAlertSuccess() {
        if (alertSuccess == null)
            alertSuccess = setElement("alertSuccess");
        return alertSuccess;
    }

    public WebElement getAlertInfo() {
        if (alertInfo == null)
            alertInfo = setElement("alertInfo");
        return alertInfo;
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
        return getElements("bb-card bb-card");
    }
}
