package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class ComponenteMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-toast-message-success")
    private WebElement alertSuccess;

    @FindBy(cssSelector = ".p-toast-message-info .p-toast-detail")
    private WebElement alertInfo;

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

//        Não da pra usar o Findby
    public List<WebElement> getListBtnFecharAlerta() {
        return getElements(".p-toast .pi-times");
    }

    //Legado
//    @FindBy(cssSelector = ".p-dropdown-trigger span")
//    private WebElement dropComponente;

//    public WebElement getDropDownComponente() {
//        if (dropComponente == null)
//            dropComponente = setElement("dropComponente");
//        return dropComponente;
//    }
//
//    public List<WebElement> getListComponente() {
//        return getElements(".p-dropdown-items-wrapper span");
//    }

    public WebElement getBtnFecharModal() {
        return getElement(".p-dialog-header .pi-times");
    }

    public WebElement getForm() {
        return getElement(".p-dialog-content form");
    }
}
