package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Razoes.CARR_ELEM;
import static utils.Utils.*;

public class IBMCloudPage extends Pagina {
    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".p-dropdown-trigger span")
    private Elemento dropDownComponente;

    @MapearElementoWeb(css = "nia-platia-table th input.p-inputtext")
    private ElementoInput inputFiltro;

    @MapearElementoWeb(css = "nia-membros-table thead .p-inputtext")
    private ElementoInput inputFiltroModal;

    @MapearElementoWeb(css = "nia-platia-table td")
    private ElementoTexto txtNenhumResultado;

    @MapearElementoWeb(css = "nia-membros-table td")
    private ElementoTexto txtNenhumResultadoModal;

    @MapearElementoWeb(css = "nia-platia-table .deleteicon span")
    private ElementoBotao btnLimparFiltro;

    @MapearElementoWeb(css = "nia-membros-table .deleteicon span")
    private ElementoBotao btnLimparFiltroModal;

    public WebElement getAlert(){
        new Utils().esperar(tempoQTeste(CARR_ELEM.getDelay()), CARR_ELEM.getRazao());
        return getElement("div .p-toast-detail");
    }

    private List<WebElement> getListComponente(){
        return getElements(".p-dropdown-items-wrapper span");
    }

    public String getAlertMensagem(){
        new Utils().capturaTela();
        return getAlert().getText();
    }

    public void acessarComponente(String componente) throws ElementoNaoLocalizadoException {
        dropDownComponente.clicar();
        List<WebElement> listComponente = getListComponente();
        for (WebElement webElement : listComponente) {
            if (webElement.getText().intern().equals(componente.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public void pesquisar(String palavra, String local) {
        try {
            switch (local) {
                case "componente":
                    inputFiltro.escrever(palavra);
                    break;
                case "modal":
                    inputFiltroModal.escrever(palavra);
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public String getTxtInputFiltro(String local) {
        try {
            switch (local) {
                case "componente":
                    return inputFiltro.recuperarTexto();
                case "modal":
                    return inputFiltroModal.recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }

    public boolean resultadosContemString(String palavraPesquisada, String local) {
        boolean resultadosOk = true;
        List<WebElement> listTxt;
        switch (local) {
            case "componente":
                listTxt = getElements("nia-platia-table td:first-child");
                break;
            case "modal":
                listTxt = getElements("nia-membros-table td:nth-child(2)");
                break;
            case "sigla":
                listTxt = getElements(".p-datatable-tbody td:nth-child(2)");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + local);
        }
        for (WebElement webElement : listTxt) {
            if (!(webElement.getText().toLowerCase().contains(palavraPesquisada.toLowerCase()))) {
                resultadosOk = false;
                break;
            }
        }
        return resultadosOk;
    }

    public String getTituloComponente() throws ElementoNaoLocalizadoException {
        return this.divTituloComponente.recuperarTexto();
    }

    public String getTxtNenhumResultado(String local) {
        try {
            switch (local) {
                case "componente":
                    return txtNenhumResultado.recuperarTexto();
                case "modal":
                    return txtNenhumResultadoModal.recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }

    public void limparPesquisa(String local) {
        try {
            switch (local) {
                case "componente":
                    btnLimparFiltro.clicar();
                    break;
                case "modal":
                    btnLimparFiltroModal.clicar();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }
}
