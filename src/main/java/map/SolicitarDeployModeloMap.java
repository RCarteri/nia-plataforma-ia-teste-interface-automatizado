package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class SolicitarDeployModeloMap extends BasePageObjects {
    @FindBy(cssSelector = ".p-button-success")
    private WebElement btnSolicitar;

    public WebElement getInputNome() {
        return getElement("#nomeDoModelo");
    }

    public WebElement getDropDownInstancia() {
        return getElement("#instancia .pi-chevron-down");
    }

    public WebElement getDropDownNotebook() {
        return getElement("p-dropdown[placeholder*='notebook'] .pi-chevron-down");
    }

    public WebElement getDropDownDataAsset() {
        return getElement("p-multiselect[defaultlabel*='data asset'] .pi-chevron-down");
    }

    public WebElement getBtnSolicitar() {
        if (btnSolicitar == null)
            btnSolicitar = setElement("btnSolicitar");
        return btnSolicitar;
    }

    public List<WebElement> getListBtnDeploy() {
        return getElements(".pi-cloud-upload");
    }

    public List<WebElement> getListElemDropDown() {
        return getElements("li.p-ripple");
    }

    public List<WebElement> getListDataAssets() {
        return getElements(".p-checkbox");
    }

    public List<WebElement> getListDataAssetsSelecionados(){
        return getElements("li.p-highlight");
    }
}
