package pagesObjects.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import utils.Utils;

public class WatsonStudioPage extends Pagina {
    @MapearElementoWeb(css = ".p-button-sm")
    private ElementoBotao btnOpcoes;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento spanMembros;


    public void clicarBotaoOpcao(String opcao) {
        try {
            if ("Membros".equals(opcao)) {
                spanMembros.clicar();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }

    }

}
