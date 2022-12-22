package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class BBCardHeaderMap extends BasePageObjects {
    @FindBy(cssSelector = "bb-card-body h1")
    private WebElement tituloComponente;

    protected WebElement getTituloComponente() {
        if (tituloComponente == null)
            tituloComponente = setElement("tituloComponente");
        return tituloComponente;
    }

    protected WebElement getInputPesquisa() {
       return getElement(".bb-textfield-group input");
    }

    protected WebElement getInputSigla() {
       return getElement(".bb-tag-field-input");
    }

    protected List<WebElement> getListSiglaDisponiveis() {
       return getElements(".menu-item a");
    }

    protected List<WebElement> getListSiglasSelecionada() {
       return getElements("bb-tag-field .bb-label-small");
    }

    protected List<WebElement> getListBtnRemoverSigla() {
       return getElements(".tag button");
    }

    protected WebElement getBtnFiltrar() {
       return getElement(".secondary");
    }

    protected WebElement getTxtTotalResultados() {
        return getElement(".status-bar label");
    }

    protected WebElement getBtnLimparTudo() {
        return getElement("bb-filter bb-link-nav");
    }
}