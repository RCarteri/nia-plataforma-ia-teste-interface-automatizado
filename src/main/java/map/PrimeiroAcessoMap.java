package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.*;

public class PrimeiroAcessoMap extends Pagina {
    @MapearElementoWeb(css = "span.p-inline-message-text")
    private ElementoTexto txtMensagem;

    @MapearElementoWeb(css = ".p-text-center .p-button-primary")
    private ElementoBotao btnAcao;

    public List<WebElement> getStepsItens() {
        return getElements(".p-steps-item");
    }

    public WebElement getBtnVoltar() {
        return getElement(".p-button-secondary");
    }

    public WebElement getMensagemConvite() {
        return waitElement(".p-toast-bottom-center .p-toast-message-success");
    }

    public WebElement getBtnAvancar() {
        return getElement(".p-ml-auto.p-button-primary");
    }

    public ElementoTexto getTxtMensagem() {
        return txtMensagem;
    }

    public ElementoBotao getBtnAcao() {
        return btnAcao;
    }
}
