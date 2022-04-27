package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;

public class ProvedorMap extends BasePageObjects {
    @FindBy(xPath = "//h3[contains(text(), 'IBM Cloud')]")
    private WebElement btnIBMCloud;

    @FindBy(xPath = "//h3[contains(text(), 'Triton')]")
    private WebElement btnTriton;

    public WebElement getBtnIBMCloud() {
        if (btnIBMCloud == null)
            btnIBMCloud = setElement("btnIBMCloud");
        return btnIBMCloud;
    }

    public WebElement getBtnTriton() {
        if (btnTriton == null)
            btnTriton = setElement("btnTriton");
        return btnTriton;
    }

    public List<WebElement> getListBtnExibir() {
        return getElements("td button.ng-star-inserted.p-button-secondary");
    }

    public List<WebElement> getListNomes() {
        return getElements("td.ng-star-inserted:first-child");
    }
}
