package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;

public class ProvedorMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-toast-message-success")
    private WebElement alertSuccess;

    public void getAlertSuccess() {
        if (alertSuccess == null)
            alertSuccess = setElement("alertSuccess");
    }

    public List<WebElement> getListaProvedores() {
        return getElements(".d-flex bb-card-body");
    }

//    Não da pra usar o Findby
    public List<WebElement> getListBtnFecharAlerta() {
        return getElements(".p-toast .pi-times");
    }
}
