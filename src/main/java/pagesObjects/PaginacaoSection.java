package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Utils.getElements;
import static utils.Utils.rolarPaginaAteElemento;

public class PaginacaoSection extends Pagina {
    public final List<WebElement> listBtnNPaginacao = getElements("button.p-paginator-page:nth-child(n+1):nth-child(-n+5)");

    @MapearElementoWeb(css = "nia-platia-table .p-paginator-current")
    private ElementoTexto txtPaginacao;

    @MapearElementoWeb(css = ".p-dialog-mask .p-paginator-current")
    private ElementoTexto txtPaginacaoModal;

    public int getQuantResultados(@NotNull String local) {
        String quantResultados = null;
        String frase = null;
        try {
            switch (local) {
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
            Utils.logError(e);
        }
        assert quantResultados != null;
        return Integer.parseInt(quantResultados);
    }

    protected void avancarPagina(WebElement nPagina) {
        System.out.println("Avançando para a página " + nPagina.getText());
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }
}
