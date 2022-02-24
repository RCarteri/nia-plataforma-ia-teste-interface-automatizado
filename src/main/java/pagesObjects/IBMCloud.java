package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Utils.getDriver;
import static utils.Utils.rolarPaginaAteElemento;

public class IBMCloud extends Pagina {

    private final List<WebElement> listBtnExibir = getDriver().findElements(By.cssSelector(".p-button-secondary"));

    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".p-dropdown-trigger span")
    private Elemento dropDownComponente;

    @MapearElementoWeb(css = "nia-platia-table th input.p-inputtext")
    private ElementoInput inputFiltro;

    @MapearElementoWeb(css = "nia-membros-table thead .p-inputtext")
    private ElementoInput inputFiltroModal;

    @MapearElementoWeb(css = "nia-platia-table .p-paginator-current")
    private ElementoTexto txtPaginacao;

    @MapearElementoWeb(css = "nia-membros-table .p-paginator-current")
    private ElementoTexto txtPaginacaoModal;

    @MapearElementoWeb(css = "nia-platia-table td")
    private ElementoTexto txtNenhumResultado;

    @MapearElementoWeb(css = "nia-membros-table td")
    private ElementoTexto txtNenhumResultadoModal;

    @MapearElementoWeb(css = "nia-platia-table .deleteicon span")
    private ElementoBotao btnLimparFiltro;

    @MapearElementoWeb(css = "nia-membros-table .deleteicon span")
    private ElementoBotao btnLimparFiltroModal;

    Utils utils = new Utils();

    public void clicarBotaoLista(int localizacao) {
        localizacao--;
        rolarPaginaAteElemento(listBtnExibir.get(localizacao));
        listBtnExibir.get(localizacao).click();
        new Utils().capturaTela();
    }

    public void acessarComponente(String componente) throws ElementoNaoLocalizadoException {
        ativarDropDown();
        List<WebElement> listComponente = getDriver().findElements(By.cssSelector(".p-dropdown-items-wrapper span"));
        for (WebElement webElement : listComponente) {
            if (webElement.getText().intern().equals(componente.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public void pesquisar(String palavra, String local){
        try {
            switch (local){
                case "componente":
                    inputFiltro.escrever(palavra);
                    break;
                case "modal":
                    inputFiltroModal.escrever(palavra);
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public String getTxtInputFiltro(String local){
        try {
            switch (local){
                case "componente":
                    return inputFiltro.recuperarTexto();
                case "modal":
                    return inputFiltroModal.recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
        return null;
    }

    public int getQuantResultados(String local) {
        String quantResultados = null;
        String frase = null;
        try {
        switch (local){
            case "componente":
                frase = txtPaginacao.recuperarTexto();
                break;
            case "modal":
                frase = txtPaginacaoModal.recuperarTexto();
                break;
        }
            assert frase != null;
            quantResultados = frase.substring(frase.indexOf("de") + 3, frase.length() - 1);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
        assert quantResultados != null;
        return Integer.parseInt(quantResultados);
    }

    public boolean resultadosContemString(String palavraPesquisada, String local){
        boolean resultadosOk = true;
        List<WebElement> listTxt;
        switch (local){
            case "componente":
                listTxt = getDriver().findElements(By.cssSelector("nia-platia-table td:first-child"));
                break;
            case "modal":
                listTxt = getDriver().findElements(By.cssSelector("nia-membros-table td:first-child"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + local);
        }
        for (int i = 0; i < listTxt.size(); i++) {
            if (!(listTxt.get(i).getText().toLowerCase().contains(palavraPesquisada.toLowerCase()))){
                resultadosOk = false;
                break;
            }
        }
        return resultadosOk;
    }

    public String getTituloComponente() throws ElementoNaoLocalizadoException {
        return this.divTituloComponente.recuperarTexto();
    }

    private void ativarDropDown() throws ElementoNaoLocalizadoException {
        dropDownComponente.clicar();
    }

    public String getTxtNenhumResultado(String local){
        try {
            switch (local){
                case "componente":
                    return txtNenhumResultado.recuperarTexto();
                case "modal":
                    return txtNenhumResultadoModal.recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
        return null;
    }

    public void limparPesquisa(String local){
        try {
            switch (local){
                case "componente":
                    btnLimparFiltro.clicar();
                    break;
                case "modal":
                    btnLimparFiltroModal.clicar();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }
}
