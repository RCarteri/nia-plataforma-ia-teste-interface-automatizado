package pagesObjects.ibmCloud;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Utils.getElements;

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

    private List<WebElement> getListSpanFuncao(){
        return getElements("p-dropdownitem .ng-star-inserted");
    }

    private List<WebElement> getListSmallMsg(){
        return getElements("small.p-invalid");
    }

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
        if (funcao.equals("")) {
            dropDownFuncao.clicar();
            inputAdicionarMembro.clicar();
            return;
        }
        for (WebElement wE : getListSpanFuncao()) {
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
        StringBuilder mensagem = new StringBuilder();
        for (WebElement webElement : getListSmallMsg()) {
            mensagem.append(webElement.getText().replaceAll("\\n", ""));
        }
        return mensagem.toString();
    }
}
