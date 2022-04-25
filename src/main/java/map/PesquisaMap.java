package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;

public class PesquisaMap extends BasePageObjects {
    @FindBy(cssSelector = "nia-platia-table th input.p-inputtext")
    private WebElement inputPesquisa;

    public WebElement getInputPesquisa() {
        if (inputPesquisa == null)
            inputPesquisa = setElement("inputPesquisa");
        return inputPesquisa;
    }

    @FindBy(cssSelector = "nia-membros-table thead .p-inputtext")
    private WebElement inputPesquisaModal;

    public WebElement getInputPesquisaModal() {
        if (inputPesquisaModal == null)
            inputPesquisaModal = setElement("inputPesquisaModal");
        return inputPesquisaModal;
    }

    @FindBy(cssSelector = "nia-platia-table .deleteicon span")
    private WebElement btnLimparPesquisa;

    public WebElement getBtnLimparPesquisa() {
        if (btnLimparPesquisa == null)
            btnLimparPesquisa = setElement("btnLimparPesquisa");
        return btnLimparPesquisa;
    }

    @FindBy(cssSelector = "nia-membros-table .deleteicon span")
    private WebElement btnLimparFiltroPesquisa;

    public WebElement getBtnLimparFiltroPesquisa() {
        if (btnLimparFiltroPesquisa == null)
            btnLimparFiltroPesquisa = setElement("btnLimparFiltroPesquisa");
        return btnLimparFiltroPesquisa;
    }

    public List<WebElement> getListaNomesModal() {
        return getElements("nia-membros-table td:nth-child(2)");
    }

    public List<WebElement> getListaSigla() {
        return getElements(".p-datatable-tbody td:nth-child(2)");
    }

    public List<WebElement> getListaNomesComponente() {
        return getElements("nia-platia-table td:first-child");
    }
}
