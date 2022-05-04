package pagesObjects.sections;

import map.PaginacaoMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import static support.Utils.rolarPaginaAteElemento;

public class PaginacaoSection {
    public String getQuantResultados(@NotNull String local) {
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
        return frase.substring(frase.indexOf("de") + 3, frase.length() - 1);
    }

    protected void avancarPagina(@NotNull WebElement nPagina) {
        System.out.println("Testando a página " + nPagina.getText() + ".");
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }
}
