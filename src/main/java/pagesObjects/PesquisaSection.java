package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import utils.Utils;

public class PesquisaSection extends Pagina {

    @MapearElementoWeb(css = "nia-platia-table th input.p-inputtext")
    private ElementoInput inputPesquisa;

    @MapearElementoWeb(css = "nia-membros-table thead .p-inputtext")
    private ElementoInput inputPesquisaModal;

    @MapearElementoWeb(css = "nia-platia-table .deleteicon span")
    private ElementoBotao btnLimparPesquisa;

    @MapearElementoWeb(css = "nia-membros-table .deleteicon span")
    private ElementoBotao btnLimparFiltroPesquisa;

    public void pesquisar(String palavra, String local) {
        try {
            switch (local) {
                case "componente":
                    inputPesquisa.escrever(palavra);
                    break;
                case "modal":
                    inputPesquisaModal.escrever(palavra);
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
                    return inputPesquisa.recuperarTexto();
                case "modal":
                    return inputPesquisaModal.recuperarTexto();
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
                    btnLimparPesquisa.clicar();
                    break;
                case "modal":
                    btnLimparFiltroPesquisa.clicar();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }
}
