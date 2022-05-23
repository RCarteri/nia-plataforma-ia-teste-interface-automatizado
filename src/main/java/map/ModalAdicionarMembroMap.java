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
    @FindBy(cssSelector = ".p-button-secondary.p-ml-auto")
    private WebElement btnAdicionarMembro;

    @FindBy(cssSelector = ".p-button-success")
    private WebElement btnConfirmar;

    @FindBy(cssSelector = ".p-component.ng-invalid")
    private WebElement inputChave;

    public List<WebElement> getListSpanFuncao() {
        return getElements("p-dropdownitem .ng-star-inserted");
    }

    public List<WebElement> getListSmallMsg() {
        return getElements("small.p-invalid");
    }

    public WebElement getBtnConfirmar() {
        if (btnConfirmar == null)
            btnConfirmar = setElement("btnConfirmar");
        return btnConfirmar;
    }

    public WebElement getBtnAdicionarMembro() {
        if (btnAdicionarMembro == null)
            btnAdicionarMembro = setElement("btnAdicionarMembro");
        return btnAdicionarMembro;
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
