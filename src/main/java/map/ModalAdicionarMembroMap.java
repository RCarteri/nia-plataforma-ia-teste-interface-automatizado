package map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ModalAdicionarMembroMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-button-success")
    private WebElement btnConfirmar;

    @FindBy(cssSelector = ".p-field input.p-inputtext")
    private WebElement inputChave;

    public List<WebElement> getListSpanFuncao() {
        return getElements("p-dropdownitem .ng-star-inserted");
    }

    public List<WebElement> getListSmallMsg() {
        return getElements("small.p-error");
    }

    public WebElement getBtnConfirmar() {
        if (btnConfirmar == null)
            btnConfirmar = setElement("btnConfirmar");
        return btnConfirmar;
    }

    public WebElement getBtnAdicionarMembro() {
        return getElement(".p-button-secondary.p-ml-auto");
    }

    public WebElement getInputChave() {
        if (inputChave == null)
            inputChave = setElement("inputChave");
        return inputChave;
    }

    public WebElement getDropDownFuncao() {
        try {
            return getElement(".p-m-3 .pi-chevron-down");
        }catch (NoSuchElementException e){
            printLog("Dropdown de função não existe nesta opção.", INFO);
            return null;
        }
    }
}
