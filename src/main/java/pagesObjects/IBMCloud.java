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
    private final List<WebElement> listTxtLinha = getDriver().findElements(By.cssSelector("nia-platia-table tr td:first-child"));
    private final List<WebElement> listTxtLinhaModal = getDriver().findElements(By.cssSelector("nia-membros-table tr td:first-child"));

    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".ng-tns-c47-5 .p-dropdown-label")
    private Elemento dropDownComponente;

    @MapearElementoWeb(css = "nia-platia-table th span .p-inputtext")
    private ElementoInput inputFiltro;

    @MapearElementoWeb(css = "nia-membros-table thead span .p-inputtext")
    private ElementoInput inputFiltroModal;

    @MapearElementoWeb(css = "nia-platia-table div .p-paginator-current")
    private ElementoTexto txtPaginacao;

    @MapearElementoWeb(css = "nia-membros-table div .p-paginator-current")
    private ElementoTexto txtPaginacaoModal;

    @MapearElementoWeb(css = "nia-platia-table tr[class='ng-star-inserted'] td")
    private ElementoTexto txtNenhumResultado;

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
        List<WebElement> listComponente = getDriver().findElements(By.cssSelector(".ng-tns-c47-5 li span"));
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

    public String getTxtInputFiltro(){
        try {
            return inputFiltro.recuperarTexto();
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
            return null;
        }
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
                listTxt = listTxtLinha;
                break;
            case "modal":
                listTxt = listTxtLinhaModal;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + local);
        }
        for (int i = 0; i < listTxt.size(); i++) {
            if (!(listTxt.get(i).getText().contains(palavraPesquisada))){
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

    public String getTxtNenhumResultado(){
        try {
            return txtNenhumResultado.recuperarTexto();
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
            return null;
        }
    }

    public void limparPesquisa(){
        try {
            btnLimparFiltro.clicar();
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }
}
