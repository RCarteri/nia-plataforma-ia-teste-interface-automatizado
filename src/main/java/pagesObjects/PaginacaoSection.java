package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import map.PaginacaoMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import static support.Utils.rolarPaginaAteElemento;

public class PaginacaoSection extends Pagina {
    public int getQuantResultados(@NotNull String local) {
        String frase = null;
        PaginacaoMap pM = new PaginacaoMap();
        switch (local) {
            case "componente":
                frase = pM.getTxtPaginacao().getText();
                break;
            case "modal":
                frase = pM.getTxtPaginacaoModal().getText();
                break;
        }
        assert frase != null;
        String quantResultados = frase.substring(frase.indexOf("de") + 3, frase.length() - 1);
        return Integer.parseInt(quantResultados);
    }

    protected void avancarPagina(@NotNull WebElement nPagina) {
        if (nPagina.getText().equals("1")) return;
        System.out.println("Avançando para a página " + nPagina.getText());
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }
}
