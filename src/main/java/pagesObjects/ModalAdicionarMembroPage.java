package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Utils.getDriver;

public class ModalAdicionarMembroPage extends Pagina {
    @MapearElementoWeb(css = ".p-button-secondary.p-ml-auto")
    private ElementoBotao btnAdicionarMembro;

    @MapearElementoWeb(css = ".p-button-success")
    private ElementoBotao btnConfirmar;

    @MapearElementoWeb(css = ".p-component.ng-invalid")
    private ElementoInput inputAdicionarMembro;

    @MapearElementoWeb(css = ".p-m-3 .pi-chevron-down")
    private Elemento dropDownFuncao;

    @MapearElementoWeb(css = "p-dropdownitem .ng-star-inserted")
    private Elemento spanFuncao;

    public void adicionarMembro(String funcao, String chave) {
        try {
            inputAdicionarMembro.escrever(chave);
            inputAdicionarMembro.clicar();
            if (dropDownFuncao.elementoExiste()) selecionarFuncao(funcao);
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    private void selecionarFuncao(String funcao) throws ElementoNaoLocalizadoException {
        dropDownFuncao.clicar();
        final List<WebElement> listSpanFuncao = getDriver().findElements(By.cssSelector("p-dropdownitem .ng-star-inserted"));
        if (funcao.equals("")) {
            dropDownFuncao.clicar();
            inputAdicionarMembro.clicar();
            return;
        }
        for (WebElement wE : listSpanFuncao) {
            if (wE.getText().equals(funcao)) {
                wE.click();
                break;
            }
        }
    }

    public void acessarAdicionarMembro() {
        try {
            btnAdicionarMembro.clicar();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public boolean isBtnConfirmarAtivo(){
        try {
            return btnConfirmar.elementoAtivo();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return true;
    }

    public String getMensagem() {
        final List<WebElement> listSmallMensagem = getDriver().findElements(By.cssSelector("small.p-invalid"));
        StringBuilder mensagem = new StringBuilder();
        for (WebElement webElement : listSmallMensagem) {
            mensagem.append(webElement.getText().replaceAll("\\n", ""));
        }
        return mensagem.toString();
    }
}
