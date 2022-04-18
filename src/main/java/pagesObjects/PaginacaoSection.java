package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.PaginacaoMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import support.Utils;

import static support.Utils.rolarPaginaAteElemento;

public class PaginacaoSection extends Pagina {
    public int getQuantResultados(@NotNull String local) {
        String quantResultados = null;
        String frase = null;
        PaginacaoMap pM = new PaginacaoMap();
        try {
            switch (local) {
                case "componente":
                    frase = pM.getTxtPaginacao().recuperarTexto();
                    break;
                case "modal":
                    frase = pM.getTxtPaginacaoModal().recuperarTexto();
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

    protected void avancarPagina(@NotNull WebElement nPagina) {
        System.out.println("Avançando para a página " + nPagina.getText());
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }
}
