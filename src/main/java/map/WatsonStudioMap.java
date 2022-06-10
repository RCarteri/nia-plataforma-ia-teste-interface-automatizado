package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;

public class WatsonStudioMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-dropdown-clearable .p-dropdown-label")
    private WebElement dropDownSigla;

    @FindBy(cssSelector = ".pi-refresh")
    private WebElement btnAtualizar;

    public WebElement getDropDownSigla() {
        if (dropDownSigla == null)
            dropDownSigla = setElement("dropDownSigla");
        return dropDownSigla;
    }

    public WebElement getBtnAtualizar() {
        if (btnAtualizar == null)
            btnAtualizar = setElement("btnAtualizar");
        return btnAtualizar;
    }

    public List<WebElement> getListaSigla() {
        return getElements("p-dropdownitem span");
    }
}
