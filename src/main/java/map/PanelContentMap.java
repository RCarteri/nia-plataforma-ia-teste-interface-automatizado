package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import static support.Utils.getElements;

public class PanelContentMap extends Pagina {
    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento segundaOpcao;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(4)")
    private Elemento terceiraOpcao;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(5)")
    private Elemento quartaOpcao;

    @MapearElementoWeb(css = "nia-platia-table td")
    private ElementoTexto txtNenhumResultado;

    @MapearElementoWeb(css = "nia-membros-table td")
    private ElementoTexto txtNenhumResultadoModal;

    public Elemento getSegundaOpcao() {
        return segundaOpcao;
    }

    public Elemento getTerceiraOpcao() {
        return terceiraOpcao;
    }

    public Elemento getQuartaOpcao() {
        return quartaOpcao;
    }

    public ElementoTexto getTxtNenhumResultado() {
        return txtNenhumResultado;
    }

    public ElementoTexto getTxtNenhumResultadoModal() {
        return txtNenhumResultadoModal;
    }

    public WebElement getPrimeiraOpcao() {
        return getElements("a.ng-star-inserted").get(0);
    }
}
