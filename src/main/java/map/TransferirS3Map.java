package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;

import static support.GetElements.getElement;

public class TransferirS3Map extends BasePageObjects {
    public WebElement getInputCodigoSeguranca() {
        return getElement("#codigoSeguranca");
    }

    public WebElement getInputCodigoAcesso() {
        return getElement("#codigoAcesso");
    }

    public WebElement getInputNomeVolume() {
        return getElement("#nomeVolume");
    }

    public WebElement getBtnBuscar() {
        return getElement(".file-container .p-button-primary");
    }

    public WebElement getBtnTransferir() {
        return getElement(".p-jc-center .p-button-primary");
    }
}