package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;

import static support.Utils.waitLoadPage;

public class MensagemMap extends Pagina {
    @MapearElementoWeb(css = ".p-toast-message-success")
    private Elemento alertSuccess;

    public Elemento getAlertSuccess() {
        waitLoadPage();
        return alertSuccess;
    }
}
