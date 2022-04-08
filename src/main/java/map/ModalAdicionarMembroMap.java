package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElements;

public class ModalAdicionarMembroMap extends Pagina {
    @MapearElementoWeb(css = ".p-button-secondary.p-ml-auto")
    private ElementoBotao btnAdicionarMembro;

    @MapearElementoWeb(css = ".p-button-success")
    private ElementoBotao btnConfirmar;

    @MapearElementoWeb(css = ".p-component.ng-invalid")
    private ElementoInput inputAdicionarMembro;

    @MapearElementoWeb(css = ".p-m-3 .pi-chevron-down")
    private Elemento dropDownFuncao;

    public List<WebElement> getListSpanFuncao() {
        return getElements("p-dropdownitem .ng-star-inserted");
    }

    public List<WebElement> getListSmallMsg() {
        return getElements("small.p-invalid");
    }

    public ElementoBotao getBtnAdicionarMembro() {
        return btnAdicionarMembro;
    }

    public ElementoBotao getBtnConfirmar() {
        return btnConfirmar;
    }

    public ElementoInput getInputAdicionarMembro() {
        return inputAdicionarMembro;
    }

    public Elemento getDropDownFuncao() {
        return dropDownFuncao;
    }
}
