package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElements;

public class ModalComponenteMap extends Pagina {
    @MapearElementoWeb(css=".p-dialog-title")
    private ElementoTexto spanTituloModal;

    @MapearElementoWeb(css=".pi-times")
    private ElementoBotao btnFechar;

    public ElementoTexto getSpanTituloModal() {
        return spanTituloModal;
    }

    public ElementoBotao getBtnFechar() {
        return btnFechar;
    }

    public List<WebElement> getListInfoNomeID() {
        return getElements(".ng-trigger h4");
    }

    public List<WebElement> getListaElementosModal() {
        return getElements(".p-dialog-content td");
    }

    public List<WebElement> getListCabecalhoNomeID() {
        return getElements("div[class*='p-ai-center ng-tns-c47'] span");
    }
}
