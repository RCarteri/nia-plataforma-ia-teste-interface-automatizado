package map;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElements;

public class PesquisaMap extends Pagina {
    @MapearElementoWeb(css = "nia-platia-table th input.p-inputtext")
    private ElementoInput inputPesquisa;

    @MapearElementoWeb(css = "nia-membros-table thead .p-inputtext")
    private ElementoInput inputPesquisaModal;

    @MapearElementoWeb(css = "nia-platia-table .deleteicon span")
    private ElementoBotao btnLimparPesquisa;

    @MapearElementoWeb(css = "nia-membros-table .deleteicon span")
    private ElementoBotao btnLimparFiltroPesquisa;

    public List<WebElement> getListaNomesModal() {
        return getElements("nia-membros-table td:nth-child(2)");
    }

    public List<WebElement> getListaSigla() {
        return getElements(".p-datatable-tbody td:nth-child(2)");
    }

    public List<WebElement> getListaNomesComponente() {
        return getElements("nia-platia-table td:first-child");
    }

    public ElementoInput getInputPesquisa() {
        return inputPesquisa;
    }

    public ElementoInput getInputPesquisaModal() {
        return inputPesquisaModal;
    }

    public ElementoBotao getBtnLimparPesquisa() {
        return btnLimparPesquisa;
    }

    public ElementoBotao getBtnLimparFiltroPesquisa() {
        return btnLimparFiltroPesquisa;
    }
}
