package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
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
    private final List<WebElement> listTxtLinha = getDriver().findElements(By.cssSelector("tr td:first-child"));

    @MapearElementoWeb(id = "p-panel-1-titlebar")
    private ElementoTexto divTituloComponente;

    @MapearElementoWeb(css = ".ng-tns-c47-5 .p-dropdown-label")
    private Elemento dropDownComponente;

    @MapearElementoWeb(css = "th .p-inputtext")
    private ElementoInput inputFiltro;

    @MapearElementoWeb(css = "div .p-paginator-current")
    private ElementoTexto txtPaginacao;

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

    public void filtrarResultados(String palavra){
        try {
            inputFiltro.escrever(palavra);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public int getQuantResultados() {
        String quantResultados = null;
        try {
            String frase = txtPaginacao.recuperarTexto();
            quantResultados = frase.substring(frase.indexOf("de") + 3, frase.length() - 1);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
        return Integer.parseInt(quantResultados);
    }

    public boolean resultadosContemString(String palavraPesquisada){
        boolean resultadosOk = true;
        for (int i = 0; i < listTxtLinha.size(); i++) {
            if (!(listTxtLinha.get(i).getText().contains(palavraPesquisada))){
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
}
