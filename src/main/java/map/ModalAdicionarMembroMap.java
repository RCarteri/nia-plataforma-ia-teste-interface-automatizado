package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;

public class ModalAdicionarMembroMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-button-secondary.p-ml-auto")
    private WebElement btnAdicionarMembro;

    @FindBy(cssSelector = ".p-m-3 .pi-chevron-down")
    private WebElement dropDownFuncao;

    @FindBy(cssSelector = ".p-button-success")
    private WebElement btnConfirmar;

    @FindBy(cssSelector = ".p-component.ng-invalid")
    private WebElement inputAdicionarMembro;

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

    public WebElement getInputAdicionarMembro() {
        if (inputAdicionarMembro == null)
            inputAdicionarMembro = setElement("inputAdicionarMembro");
        return inputAdicionarMembro;
    }

    public WebElement getDropDownFuncao() {
        if (dropDownFuncao == null)
            dropDownFuncao = setElement("dropDownFuncao");
        return dropDownFuncao;
    }
}
