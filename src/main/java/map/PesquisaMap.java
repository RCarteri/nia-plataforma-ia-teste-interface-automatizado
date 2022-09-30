package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.annotations.FindBy;

import java.util.List;

import static org.junit.Assert.fail;
import static support.GetElements.getElements;

public class PesquisaMap extends BasePageObjects {
    @FindBy(cssSelector = "nia-platia-table th input.p-inputtext")
    private WebElement inputPesquisa;

    @FindBy(cssSelector = "span.deleteicon.p-mb-3 input")
    private WebElement inputPesquisaModal;

    @FindBy(cssSelector = "nia-platia-table .deleteicon span")
    private WebElement btnLimparPesquisa;

    @FindBy(cssSelector = "span.deleteicon.p-mb-3 span")
    private WebElement btnLimparPesquisaModal;

    public WebElement getInputPesquisa() {
        if (inputPesquisa == null)
            inputPesquisa = setElement("inputPesquisa");
        return inputPesquisa;
    }

    public WebElement getInputPesquisaModal() {
        if (inputPesquisaModal == null)
            inputPesquisaModal = setElement("inputPesquisaModal");
        return inputPesquisaModal;
    }

    public WebElement getBtnLimparPesquisa() {
        if (btnLimparPesquisa == null)
            btnLimparPesquisa = setElement("btnLimparPesquisa");
        return btnLimparPesquisa;
    }

    public WebElement getBtnLimparPesquisaModal() {
        if (btnLimparPesquisaModal == null)
            btnLimparPesquisaModal = setElement("btnLimparPesquisaModal");
        return btnLimparPesquisaModal;
    }

    public List<WebElement> getListaNomesModal() {
        return getElements(".p-dialog-content td:nth-child(1)");
    }

    public List<WebElement> getListaSigla() {
        return getElements(".p-datatable-tbody td:nth-child(2)");
    }

    public List<WebElement> getListaNomesComponente() {
        if (getElements("nia-platia-table td:nth-child(2)").size() == 0)
            fail("A lista não possui nenhum item.");
        return getElements("nia-platia-table td:first-child");
    }
}
