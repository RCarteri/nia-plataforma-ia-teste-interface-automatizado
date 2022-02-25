package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import utils.Utils;

public class ModalAdicionarMembroPage extends Pagina {

    @MapearElementoWeb(css = ".p-button-secondary.p-ml-auto")
    private ElementoBotao btnAdicionarMembro;

    @MapearElementoWeb(css = ".p-button-success")
    private ElementoBotao btnConfirmar;

    @MapearElementoWeb(css = ".p-component.ng-invalid")
    private ElementoInput inputAdicionarMembro;

    @MapearElementoWeb(css = "small.p-invalid")
    private ElementoTexto smallMensagem;

    public void adicionarMembro(String chave) {
        try {
            inputAdicionarMembro.escrever(chave);
            if (isBtnConfirmarAtivo()) btnConfirmar.clicar();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public void acessarAdicionarMembro(){
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

    public String getMensagem(){
        try {
            return smallMensagem.recuperarTexto().replaceAll("\\n", "");
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }
}
