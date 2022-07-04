package map;

import org.openqa.selenium.WebElement;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class TritonMap {
    protected WebElement getBtnMaisDetalhes() {
        return getElement(".p-dialog-content button");
    }

    protected WebElement getTdNomeModelo() {
        return getElement(".p-dialog-content td:first-child");
    }

    protected WebElement getBtnPreMaisDetalhes() {
        return getElement(".p-dialog-content pre");
    }

    public WebElement getElementRequestOriginal() {
        return getElement("#request");
    }

    protected List<WebElement> getInformacoes() {
        return getElements(".p-mb-2 span");
    }

    protected WebElement getBtnExecutar() {
        return getElement(".p-button-success");
    }

    public String getRequestTxt() {
        return getElementRequestOriginal().getAttribute("value");
    }

    protected WebElement getBtnLimpar() {
        return getElement(".pi-refresh");
    }
}
