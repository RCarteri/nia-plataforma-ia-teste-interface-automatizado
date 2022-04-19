package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElements;

public class ProvedorMap extends Pagina {
    @MapearElementoWeb(css = "figure .mi--person")
    private Elemento btnPerfil;

    @MapearElementoWeb(xPath = "//h3[contains(text(), 'IBM Cloud')]")
    private ElementoTexto btnIBMCloud;

    @MapearElementoWeb(xPath = "//h3[contains(text(), 'Triton')]")
    private ElementoTexto btnTriton;

    public List<WebElement> getListBtnExibir() {
        return getElements("td button.ng-star-inserted.p-button-secondary");
    }

    public List<WebElement> getListNomes() {
        return getElements("td.ng-star-inserted:first-child");
    }

    public ElementoTexto getBtnIBMCloud() {
        return btnIBMCloud;
    }

    public ElementoTexto getBtnTriton() {
        return btnTriton;
    }
}
