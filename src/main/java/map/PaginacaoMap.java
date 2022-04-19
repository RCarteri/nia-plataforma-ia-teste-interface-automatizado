package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElements;

public class PaginacaoMap extends Pagina {
    @MapearElementoWeb(css = "nia-platia-table .p-paginator-current")
    private ElementoTexto txtPaginacao;

    @MapearElementoWeb(css = ".p-dialog-mask .p-paginator-current")
    private ElementoTexto txtPaginacaoModal;

    public ElementoTexto getTxtPaginacao() {
        return txtPaginacao;
    }

    public ElementoTexto getTxtPaginacaoModal() {
        return txtPaginacaoModal;
    }

    public List<WebElement> getListBtnNPaginacao() {
        return getElements("button.p-paginator-page:nth-child(n+2):nth-child(-n+5)");
    }
}
