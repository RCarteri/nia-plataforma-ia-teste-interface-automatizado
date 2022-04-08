package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElements;

public class WatsonStudioMap extends Pagina {
    @MapearElementoWeb(css = ".p-dropdown-clearable .pi-chevron-down")
    private Elemento dropDownSigla;

    @MapearElementoWeb(css = ".pi-refresh")
    private ElementoBotao btnAtualizar;

    public List<WebElement> getListaSigla() {
        return getElements("p-dropdownitem span");
    }

    public Elemento getDropDownSigla() {
        return dropDownSigla;
    }

    public ElementoBotao getBtnAtualizar() {
        return btnAtualizar;
    }
}
