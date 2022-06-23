package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class PaginacaoMap extends BasePageObjects {
    @FindBy(cssSelector = "nia-platia-table .p-paginator-current")
    private WebElement txtPaginacao;

    @FindBy(cssSelector = ".p-dialog-mask .p-paginator-current")
    private WebElement txtPaginacaoModal;

    public List<WebElement> getListBtnNPaginacao() {
        return getElements("nia-platia-table .p-paginator-page:nth-child(n+1):nth-child(-n+5)");
    }

    public WebElement getTxtPaginacao() {
        if (txtPaginacao == null)
            txtPaginacao = setElement("txtPaginacao");
        return txtPaginacao;
    }

    public WebElement getTxtPaginacaoModal() {
        if (txtPaginacaoModal == null)
            txtPaginacaoModal = setElement("txtPaginacaoModal");
        return txtPaginacaoModal;
    }

    public WebElement getBtnAvancarPagina(){
        return getElement(".pi-angle-right");
    }

    public WebElement getPaginaAtual(){
        return getElement(".p-highlight");
    }
}
