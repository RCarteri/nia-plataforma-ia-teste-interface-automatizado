package map.legado;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class PrimeiroAcessoMap extends Pagina {
    @MapearElementoWeb(css = "span.p-inline-message-text")
    private ElementoTexto txtMensagem;

    public List<WebElement> getStepsItens() {
        return getElements(".p-steps-item");
    }

    public WebElement getBtnVoltar() {
        return getElement(".p-button-secondary");
    }

    public WebElement getBtnAvancar() {
        return getElement(".p-ml-auto.p-button-primary");
    }

    public ElementoTexto getTxtMensagem() {
        return txtMensagem;
    }

    public WebElement getBtnAcao() {
        return getElement(".p-text-center .p-button-primary");
    }
}
